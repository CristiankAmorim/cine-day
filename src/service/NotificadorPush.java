package service;

import java.util.List;

import model.Recomendacao;
import model.Usuario;

public interface NotificadorPush {

	/**
	 * Envia uma notificação ao usuário sobre novas recomendações de filmes.
	 * @param usuario
	 * @param recomendacoes
	 */
	void enviarNotificacao(Usuario usuario, List<Recomendacao> recomendacoes);
}
