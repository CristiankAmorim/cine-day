package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import model.Filme;
import model.PerfilCinefilo;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;
import service.FiltroFilmes;

@Tag("unitario")
@DisplayName("Conferir regras de filtragem do catálogo")
public class FiltroFilmesTest {
	
	private FiltroFilmes filtro;
	private PerfilCinefilo perfil;
	private Filme filme, filme2;
	
	@BeforeEach
	void setUp() {
		filtro = new FiltroFilmes();
		perfil = new PerfilCinefilo();
		perfil.adicinarIdioma(Idioma.INGLES);
		perfil.adicinarIdioma(Idioma.PORTUGUES);
		perfil.setClassificacaoMaxima(ClassificacaoEtaria.DEZESSEIS);
		filme = new Filme("F07", "+ Velozes + Furiosos", 2003, 107, Arrays.asList(Genero.ACAO),
				ClassificacaoEtaria.QUATORZE, Idioma.INGLES, 84);
		filme2 = new Filme("F08", "Idioma não aceito", 2008, 107, Arrays.asList(Genero.ACAO),
				ClassificacaoEtaria.QUATORZE, Idioma.JAPONES, 59);
	}
	
	@Test
	@DisplayName("deve_Remover_Filme_Ja_Assistido")
	void deve_Remover_Filme_Ja_Assistido() {
		perfil.marcarFilmeComoAssistido(filme);
		
		List<Filme> resultado = filtro.filtrar(Collections.singletonList(filme), perfil);
		
		assertFalse(resultado.contains(filme));
    
	}
	
	@Test
	@DisplayName("deve_Remover_Filme_Quando_Classificacao_Acima_Da_Maxima")
	void deve_Remover_Filme_Quando_Classificacao_Acima_Da_Maxima() {
		perfil.setClassificacaoMaxima(ClassificacaoEtaria.DOZE);
		
		List<Filme> resultado = filtro.filtrar(Collections.singletonList(filme), perfil);
		
		assertFalse(resultado.contains(filme));
	}
	
	@Test
	@DisplayName("deve_remover_Filme_Quando_Idioma_Nao_Aceito")
	void deve_Remover_Filme_Quando_Idioma_Nao_Aceito() {
		perfil.adicinarIdioma(Idioma.FRANCES);
		
		List<Filme> resultado = filtro.filtrar(Collections.singletonList(filme2), perfil);
		
		assertFalse(resultado.contains(filme2));
	}
	
	@Test
	@DisplayName("deve_Remover_Filme_Com_Genero_De_Peso_0")
	void deve_Remover_Filme_Com_Genero_De_Peso_0() {
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 0.0);
		perfil.inserirPesoDoGenero(Genero.ACAO, 0.0);
		
		List<Filme> resultado = filtro.filtrar(Collections.singletonList(filme), perfil);
		
		assertFalse(resultado.contains(filme));
	}
	
	@Test
	@DisplayName("deve_Retornar_Lista_Vazia_Se_Catalogo_Vazio")
	void deve_Retornar_Lista_Vazia_Se_Catalogo_Vazio() {
		perfil.inserirPesoDoGenero(Genero.ACAO, 0.0);
		
		List<Filme> resultado = filtro.filtrar(Collections.singletonList(filme), perfil);
		
		assertNotNull(resultado);
		assertTrue(resultado.isEmpty());
	}
	
	
}
