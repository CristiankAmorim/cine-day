package service;

import model.Filme;
import model.PerfilCinefilo;

public class FiltroFilmes {

	
	
	private boolean naoFoiAssistido(Filme filme, PerfilCinefilo perfil) {
		return !perfil.filmeJaAssistido(filme);
	}
	
	private boolean classificacaoAceitavel(Filme filme, PerfilCinefilo perfil) {
		return filme.getClassificacao().dentroDaClassificacaoMaxima(perfil.getClassificacaoMaxima());
	}
	
	private boolean idiomasAceitos(Filme filme, PerfilCinefilo perfil) {
		return perfil.getIdiomasAceitos().isEmpty() || perfil.getIdiomasAceitos().contains(filme.getIdioma());
	}
}
