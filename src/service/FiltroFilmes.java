package service;

import model.Filme;
import model.PerfilCinefilo;
import model.enums.Genero;

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
	
	private boolean nenhumGeneroBloqueado(Filme filme, PerfilCinefilo perfil) {
		return filme.getGeneros().stream()
				.noneMatch(genero -> isPesoBloqueado(genero, perfil));
	}
	
	private boolean isPesoBloqueado(Genero genero, PerfilCinefilo perfil) {
		return perfil.getPesoDoGenero().containsKey(genero) && perfil.getPesoDoGenero(genero) == 0.0;
	}
}
