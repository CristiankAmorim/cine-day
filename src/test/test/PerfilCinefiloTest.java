package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import exceptions.DuracaoInvalidaException;
import exceptions.NotaInvalidaException;
import exceptions.PesoInvalidoException;
import model.Filme;
import model.PerfilCinefilo;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;

@Tag("unitario")
@DisplayName("PerfilCinefilo - validações")
public class PerfilCinefiloTest {
	
	private PerfilCinefilo perfil;
	private Filme filmeAcao;
	private Filme filmeComedia;
	
	@BeforeEach
	void setUp() {
		perfil = new PerfilCinefilo();
		filmeAcao = new Filme("F03", "Assasino Sem Rastro", 2022, 114,
				Arrays.asList(Genero.ACAO), ClassificacaoEtaria.DEZESSEIS, Idioma.PORTUGUES, 58);
		filmeComedia = new Filme("F04", "Os Parças", 2017, 100,
				Arrays.asList(Genero.COMEDIA), ClassificacaoEtaria.QUATORZE, Idioma.PORTUGUES, 41);
	}
	
	@Test
	@DisplayName("deve_Criar_Perfil_Quando_PesosValidos")
	void deve_Criar_Perfil_Quando_PesosValidos() {
		assertDoesNotThrow(() -> {
			perfil.inserirPesoDoGenero(Genero.ACAO, 0.8);
			perfil.inserirPesoDoGenero(Genero.COMEDIA, 0.4);
		});
		assertEquals(0.8, perfil.getPesoDoGenero(Genero.ACAO));
		assertEquals(0.4, perfil.getPesoDoGenero(Genero.COMEDIA));
	}
	
	@ParameterizedTest
	@CsvSource({"-3.0", "-2.0", "6.5"})
	@DisplayName("deve_Lançar_PesoInvalidoException_Quando_PesoInvalido")
	void deve_Lancar_PesoInvalidoException_Quando_PesoInvalido(double pesoInvalido) {
		assertThrows(PesoInvalidoException.class,
				() -> perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, pesoInvalido));
	}
	
	@Test
	@DisplayName("deve_Lancar_DuracaoInvalidaException_Quando_DuracaoMin_Maior_Que_DuracaMax")
	void deve_Lancar_DuracaoInvalidaException_Quando_DuracaoMin_Maior_Que_Max() {
		assertThrows(DuracaoInvalidaException.class, 
				() -> perfil.inserirFaixaDeDuracao(120, 100));
	}
	
	@ParameterizedTest
	@CsvSource({"7", "-2", "10"})
	@DisplayName("deve_Lancar_NotaInvalidaException_Quando_Nota_Fora_Do_Intervalo")
	void deve_Lancar_NotaInvalidaException_Quando_Nota_Fora_Do_Intervalo(int notaInvalida) {
		assertThrows(NotaInvalidaException.class,
				() -> perfil.atribuirNotaAoFilme(filmeAcao, notaInvalida));
	}
	
	@Test
	@DisplayName("deve_Marcar_Filme_Como_Assistido_Quando_Registrado_No_Historico")
	void deve_Marcar_Filme_Como_Assistido_Quando_Registrado() {
		perfil.marcarFilmeComoAssistido(filmeComedia);
		assertTrue(perfil.filmeJaAssistido(filmeComedia));
		assertTrue(perfil.getHistoricoFilmesAssistidos().contains(filmeComedia));
	}
	
}
