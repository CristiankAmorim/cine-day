package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import model.Filme;
import model.PerfilCinefilo;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;
import service.CalculadoraScore;

@Tag("unitario")
@DisplayName("Calculo de compatibilidade do filme com o perfil do usuario")
public class CalculadoraScoreTest {

	private CalculadoraScore calculadora;
	private PerfilCinefilo perfil;
	private Filme filme, filme2;
	
	@BeforeEach
	void setUp() {
		calculadora = new CalculadoraScore();
		perfil = new PerfilCinefilo();
		perfil.inserirFaixaDeDuracao(90, 140);
		perfil.setClassificacaoMaxima(ClassificacaoEtaria.DEZESSEIS);
		perfil.adicinarIdioma(Idioma.PORTUGUES);
		filme = new Filme("F05", "Duna: Parte Dois", 2024, 166, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),
				ClassificacaoEtaria.QUATORZE, Idioma.INGLES, 84);
		filme2 = new Filme("F06", "Duracao Acima do Limite", 2024, 180, Arrays.asList(Genero.FICCAO_CIENTIFICA, Genero.DRAMA),
				ClassificacaoEtaria.QUATORZE, Idioma.INGLES, 84);
	}
	
	@Test
	@DisplayName("deve_Retornar_Score_100_Do_Genero_Quando_Todos_Generos_Sao_Amados")
	void deve_Retoranar_Score_100_Do_Componente_Genero() {
		perfil.inserirPesoDoGenero(Genero.DRAMA, 1.0);
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 1.0);
		
		double scoreDoGenero = calculadora.calcularScoreDoGenero(filme, perfil);
		
		assertEquals(100, scoreDoGenero);
		
	}
	
	@Test
	@DisplayName("deve_Retornar_Score_Baixo_Quando_Generos_Nao_Preferidos")
	void deve_Retornar_Score_Baixo_Quando_Generos_Nao_Preferidos() {
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 0.1);
		perfil.inserirPesoDoGenero(Genero.DRAMA, 0.1);
		
		double score = calculadora.calcularScore(filme, perfil);
		
		assertTrue(score < 50, "Score abaxio de 50 é considerado baixo");
	}
	
	@Test
	@DisplayName("deve_Retornar_Score_100_Da_Duracao_Quando_Dentro_Da_Ideal")
	void deve_Retornar_Score_100_Do_Componente_Duracao() {
		perfil.inserirFaixaDeDuracao(100, 170);
		
		double scoreDaDuracao = calculadora.calcularScoreDaDuracao(filme, perfil);
		
		assertEquals(100, scoreDaDuracao);
	}
	
	@Test
	@DisplayName("deve_Reduzir_Score_Quando_Duracao_Do_Filme_Exceder_30_Min")
	void deve_Reduzir_Score_Quando_Duracao_Exceder_30_Min() {
		perfil.inserirFaixaDeDuracao(90, 170);
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 1.0);
		perfil.inserirPesoDoGenero(Genero.DRAMA, 1.0);
		
		double scoreFilmeNaFaixa = calculadora.calcularScore(filme, perfil);
		double scoreFilmeForaDaFaixa = calculadora.calcularScore(filme2, perfil);
		
		assertTrue(scoreFilmeNaFaixa > scoreFilmeForaDaFaixa);
	
	}
	
	@Test
	@DisplayName("deve_Nao_Passar_De_100_Quando_Score_Calculado")
	void deve_Nao_Passar_De_100_Qundo_Score_Calculado() {
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 1.0);
		perfil.inserirPesoDoGenero(Genero.DRAMA, 1.0);
		perfil.inserirFaixaDeDuracao(120, 170);
		perfil.adicinarIdioma(Idioma.INGLES);
		
		double score = calculadora.calcularScore(filme, perfil);
		
		assertTrue(score <= 100);		
	}
	
	@Test
	@DisplayName("deve_Nao_Ficar_Negativo_Score_Quando_Filme_Incompativel")
	void deve_Nao_Ficar_Negativo_Score_Quando_Filme_Incompativel() {
		perfil.inserirPesoDoGenero(Genero.FICCAO_CIENTIFICA, 0.1);
		perfil.inserirPesoDoGenero(Genero.DRAMA, 0.1);
		perfil.inserirFaixaDeDuracao(100, 130);
		
		double score = calculadora.calcularScore(filme2, perfil);
		
		assertTrue(score >= 0);
	}
	
}
