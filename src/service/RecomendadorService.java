package service;

import java.util.logging.Logger;

public class RecomendadorService {

	private static final Logger logger = Logger.getLogger(RecomendadorService.class.getName());
	
	private CatalogoFilmesAPI catalogoAPI;
	private HistoricoUsuarioRepository historicoUsuario;
	private NotificadorPush notificadorPush;
}
