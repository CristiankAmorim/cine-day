package service;

import java.util.Collections;
import java.util.List;
import model.Filme;
import util.GeradorAleatorio;

public class RecomendadorService {

	
	private CatalogoFilmesAPI catalogoAPI;
	private HistoricoUsuarioRepository historicoUsuario;
	private NotificadorPush notificadorPush;
	private GeradorAleatorio geradorAleatorio;
	private CalculadoraScore calculadoraScore;
	private FiltroFilmes filtroFilmes;
	
	private List<Filme> buscarCatalogo() {
		try {
			List<Filme> filmes = catalogoAPI.buscarTodos();
			return filmes != null ? filmes : Collections.emptyList();
		} catch(Exception e) {
			System.out.println("Falha ao buscar o catálogo: " + e.getMessage());
			return Collections.emptyList();
		}
	}
	
}
