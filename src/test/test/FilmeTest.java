package test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import model.Filme;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;

@Tag("unitario")
@DisplayName("Filme - teste de atributos")
public class FilmeTest {

	private Filme filme;
	
	@BeforeEach
	void setUp() {
		filme = new Filme("F01", "Quebrando Regras", 2008, 110, Arrays.asList(Genero.ACAO, Genero.DRAMA),
				ClassificacaoEtaria.QUATORZE, Idioma.PORTUGUES, 66);
	}
	
	@Test
	@DisplayName("Todos os atributos devem ser preenchidos corretamente quando o filme for criado")
	void deve_Preencher_Todos_Atributos_Quando_Filme_Criado() {
		assertAll(
				() -> assertEquals("F01", filme.getId()),
				() -> assertEquals("Quebrando Regras", filme.getTitulo()),
				() -> assertEquals(2008, filme.getAno()),
				() -> assertEquals(110, filme.getDuracaoMinutos()),
				() -> assertEquals(ClassificacaoEtaria.QUATORZE, filme.getClassificacao()),
				() -> assertEquals(Idioma.PORTUGUES, filme.getIdioma()),
				() -> assertEquals(66, filme.getPopularidade())
				);
	}
	
	@Test
	@DisplayName("O filme deve conter gêneros quando criado")
	void deve_Conter_Generos_Quando_Filme_Criado() {
		List<Genero> generos = filme.getGeneros();
		assertAll(
				() -> assertTrue(generos.contains(Genero.ACAO)),
				() -> assertTrue(generos.contains(Genero.DRAMA)),
				() -> assertFalse(generos.contains(Genero.TERROR))
				);
	}
	
	@Test
	@DisplayName("Filmes com mesmo id devem ser considerados iguais")
	void deve_Considerar_Iguais_Filmes_Com_Mesmo_Id() { 
		Filme outroFilmeComMesmoId = new Filme("F01", "Quebrando a Banca", 2008, 123, Arrays.asList(Genero.ACAO),
				ClassificacaoEtaria.QUATORZE, Idioma.INGLES, 68);
		assertEquals(filme.getId(), outroFilmeComMesmoId.getId());
	}
	
	@Test
	@DisplayName("Filmes com id distintos devem ser considerados diferentes")
	void deve_Considerar_Diferentes_Filmes_Com_Id_Distinto() {
		Filme filmeDistinto = new Filme("F02", "Batman: O Cavaleiro das Trevas", 2008, 152, Arrays.asList(Genero.ACAO),
				ClassificacaoEtaria.QUATORZE, Idioma.INGLES, 91);
		assertNotEquals(filme, filmeDistinto);
	}
	
}
