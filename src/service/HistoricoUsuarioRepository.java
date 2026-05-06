package service;

import java.util.List;

import model.Recomendacao;
import model.Usuario;

public interface HistoricoUsuarioRepository {

	/**
	 * Consulta a lista de recomendações feitas a um usuário.
	 * @param usuario
	 * @param recomendacoes
	 */
	void registrarRecomendacao(Usuario usuario, List<Recomendacao> recomendacoes);
}
