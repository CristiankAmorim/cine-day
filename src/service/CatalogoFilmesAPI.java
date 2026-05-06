package service;

import java.util.List;

import model.Filme;

public interface CatalogoFilmesAPI {
	
	/**
	 * Retorna todos os filmes disponíveis no catálogo. 
	 * @return
	 */
	List<Filme> buscarTodos();
}
