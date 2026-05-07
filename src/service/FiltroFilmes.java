package service;

import model.Filme;
import model.PerfilCinefilo;

public class FiltroFilmes {

	private boolean naoFoiAssistido(Filme filme, PerfilCinefilo perfil) {
		return !perfil.filmeJaAssistido(filme);
	}
}
