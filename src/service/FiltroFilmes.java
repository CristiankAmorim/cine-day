package service;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import model.Filme;
import model.PerfilCinefilo;
import model.enums.Genero;

public class FiltroFilmes {

	/**
	 * Filtrar os filmes do catálogo, removendo os que violam alguma regra do perfil.
	 * @param filmes
	 * @param perfil
	 * @return
	 */
	public Set<Filme> filtrar(Set<Filme> filmes, PerfilCinefilo perfil) {
		if(filmes.isEmpty() || filmes == null) {
			return Collections.emptySet();
		}
		return (Set<Filme>) filmes.stream()
				.filter(filme -> naoFoiAssistido(filme, perfil))
				.filter(filme -> classificacaoAceitavel(filme, perfil))
				.filter(filme -> idiomasAceitos(filme, perfil))
				.filter(filme -> nenhumGeneroBloqueado(filme, perfil))
				.collect(Collectors.toSet());
	}
	
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
