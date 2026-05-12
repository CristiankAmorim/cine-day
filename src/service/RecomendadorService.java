package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.Filme;
import model.Recomendacao;
import model.Usuario;
import util.GeradorAleatorio;

public class RecomendadorService {

	
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

	/*public List<Recomendacao> recomendar(Usuario usuario, int topN) {
		List<Filme> catalogo = buscarCatalogo();
		
		if(catalogo.isEmpty()) {
			return Collections.emptyList();
		}
		
		List<Filme> filmesFiltrados = filtroFilmes.filtrar(catalogo, usuario.getPerfil());
		if(filmesFiltrados.isEmpty()) {
			return Collections.emptyList();
		}
		
		//List<Recomendacao> recomendacoes = 
	}*/

	private List<Filme> buscarCatalogo() {
		try {
			List<Filme> filmes = catalogoAPI.buscarTodos();
			return filmes != null ? filmes : Collections.emptyList();
		} catch(Exception e) {
			System.out.println("Falha ao buscar o catálogo: " + e.getMessage());
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
			System.out.println("Falha ao registrar recomendação no histórico: " + e.getMessage());
		}
	}
	
}
