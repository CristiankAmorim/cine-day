package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import model.Filme;
import model.Recomendacao;
import model.Usuario;
import util.GeradorAleatorio;

public class RecomendadorService {

	private static final Logger logger = Logger.getLogger(RecomendadorService.class.getName());
	
	private CatalogoFilmesAPI catalogoAPI;
	private HistoricoUsuarioRepository historicoUsuario;
	private NotificadorPush notificadorPush;
	private GeradorAleatorio geradorAleatorio;
	private CalculadoraScore calculadoraScore;
	private FiltroFilmes filtroFilmes;
	
	public RecomendadorService(CatalogoFilmesAPI catalogoAPI, HistoricoUsuarioRepository historicoUsuario,
			NotificadorPush notificadorPush, GeradorAleatorio geradorAleatorio, CalculadoraScore calculadoraScore,
			FiltroFilmes filtroFilmes) {
		this.catalogoAPI = catalogoAPI;
		this.historicoUsuario = historicoUsuario;
		this.notificadorPush = notificadorPush;
		this.geradorAleatorio = geradorAleatorio;
		this.calculadoraScore = calculadoraScore;
		this.filtroFilmes = filtroFilmes;
	}

	public List<Recomendacao> recomendar(Usuario usuario, int topN) {
		List<Filme> catalogo = buscarCatalogo();
		
		if(catalogo.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Filme> filmesFiltrados = filtroFilmes.filtrar(catalogo, usuario.getPerfil());
		if(filmesFiltrados.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Recomendacao> recomendacoes = pontuarFilmes(filmesFiltrados, usuario);
		List<Recomendacao> recomendacoesRanqueadas = ranquearRecomendacoes(recomendacoes, topN);
		
		registrarRecomendacaoNoHistorico(usuario, recomendacoesRanqueadas);
		notificarSeNotificacaoHabilitada(usuario, recomendacoesRanqueadas);
		
		return recomendacoesRanqueadas;
	}

	/**
	 * Método que retorna uma recomendação de forma aleatória dentre os filmes filtrados.
	 * @param usuario
	 * @return
	 */
	public Optional<Recomendacao> recomendarFilmeAleatorio(Usuario usuario) {
		List<Filme> catalogo = buscarCatalogo();
		List<Filme> filmesFiltrados = filtroFilmes.filtrar(catalogo, usuario.getPerfil());
		
		if(filmesFiltrados.isEmpty()) {
			return Optional.empty();
		}
		
		int indiceDoFilme = geradorAleatorio.sortearNumeroInteiro(0, filmesFiltrados.size() - 1);
		Filme filmeSorteado = filmesFiltrados.get(indiceDoFilme);
		double score = calculadoraScore.calcularScore(filmeSorteado, usuario.getPerfil());
		
		return Optional.of(new Recomendacao(filmeSorteado, score));
	}
	
	private List<Filme> buscarCatalogo() {
		try {
			List<Filme> filmes = catalogoAPI.buscarTodos();
			return filmes != null ? filmes : Collections.emptyList();
		} catch(Exception e) {
			logger.warning("Falha ao buscar o catálogo: " + e.getMessage());
			return Collections.emptyList();
		}
	}

	private List<Recomendacao> pontuarFilmes(List<Filme> filmes, Usuario usuario) {
		return filmes.stream()
				.map(filme -> criarRecomendacao(filme, usuario))
				.collect(Collectors.toList());
	}
	
	private Recomendacao criarRecomendacao(Filme filme, Usuario usuario) {
		double score = calculadoraScore.calcularScore(filme, usuario.getPerfil());
		return new Recomendacao(filme, score);
	}
	
	private List<Recomendacao> ranquearRecomendacoes(List<Recomendacao> recomendacoes, int topN ) {
		Comparator<Recomendacao> comparador = Comparator
				.comparingDouble(Recomendacao::getScore).reversed()
				.thenComparingDouble(r -> r.getFilme().getPopularidade())
				.thenComparingInt(r -> geradorAleatorio.sortearNumeroInteiro(0, 1));
		return recomendacoes.stream()
				.sorted(comparador)
				.limit(topN)
				.collect(Collectors.toList());
	}
	
	private void registrarRecomendacaoNoHistorico(Usuario usuario, List<Recomendacao> recomendacoes) {
		try {
			historicoUsuario.registrarRecomendacao(usuario, recomendacoes);
		} catch(Exception e) {
			logger.warning("Falha ao registrar recomedações no histórico: " + e.getMessage());
		}
	}
	
	private void notificarSeNotificacaoHabilitada(Usuario usuario, List<Recomendacao> recomendacoes) {
		if(!usuario.isNotificacoesHabilitadas()) {
			logger.warning("As notificações estão desabilitadas");
		}
		
		try {
			notificadorPush.enviarNotificacao(usuario, recomendacoes);
		} catch (Exception e) {
			logger.warning("Falha ao enviar a notificação: " + e.getMessage());
		}
	}
	
}
