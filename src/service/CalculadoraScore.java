package service;

import java.util.List;
import java.util.Map;

import model.Filme;
import model.PerfilCinefilo;
import model.enums.Genero;

public class CalculadoraScore {

	private static final double PESO_GENERO = 0.50;
	private static final double PESO_DURACAO = 0.20;
	private static final double PESO_POPULARIDADE = 0.15;
	private static final double PESO_AFINIDADE = 0.15;
	
	private static final double SCORE_MAXIMO = 100.0;
	private static final double SCORE_MINIMO = 0.0;
	private static final double NOTA_ALTA = 4.0; // Nota mínima para ser considerada alta para poder dar o bônus por afinidade.
	private static final double BONUS_AFINIDADE = 100.0;
	private static final int MINUTOS_LIMITE = 30; // Filmes com 30 minutos acima do limite recebem penalização.
	
	/**
	 * Calcula o score de um filme para o perfil fornecido.
	 * @param filme filme a ser avaliado.
	 * @param perfil perfil para analisar as preferências.
	 * @return score final entre 0.0 e 100.0
	 */
	public double calcularScore(Filme filme, PerfilCinefilo perfil) {
		double scoreDoGenero = calcularScoreDoGenero(filme, perfil);
		double scoreDaDuracao = calcularScoreDaDuracao(filme, perfil);
		double scoreDaPopularidade = filme.getPopularidade();
		double scoreDaAfinidade = calcularScoreDaAfinidade(filme, perfil);
		
		double scoreTotal = (scoreDoGenero * PESO_GENERO)
						  + (scoreDaDuracao * PESO_DURACAO)
						  + (scoreDaPopularidade * PESO_POPULARIDADE)
						  + (scoreDaAfinidade * PESO_AFINIDADE);
		return Math.min(SCORE_MAXIMO, Math.max(SCORE_MINIMO, scoreTotal)); // Função para evitar que o score fique fora do limite (0.0 a 100.0.
	}                                                                      // Math.min retorna o menor valor da comparação.
	                                                                       // Math.max retorna o maior valor da comparação.
	private double calcularScoreDoGenero(Filme filme, PerfilCinefilo perfil) {
		List<Genero> generos = filme.getGeneros();
		
		if(generos.isEmpty()) {
			return SCORE_MINIMO;
		}
		
		double somaDosPesos = generos.stream()
				.mapToDouble(g -> perfil.getPesoDoGenero(g))
				.sum();
		return (somaDosPesos / generos.size()) * SCORE_MAXIMO;
	}
	
	private double calcularScoreDaDuracao(Filme filme, PerfilCinefilo perfil) {
		int duracaoDoFilme = filme.getDuracaoMinutos();
		int duracaoMinimaPreferida = perfil.getDuracaoMinimaMinutos();
		int duracaoMaximaPreferida = perfil.getDuracaoMaximaMinutos();
		
		if(duracaoDoFilme >= duracaoMinimaPreferida && duracaoDoFilme <= duracaoMaximaPreferida) {
			return SCORE_MAXIMO;
		}
		// calcula o desvio de duração do filme, ou seja, quantos minutos estão fora da faixa adequada, seja pra mais ou pra menos.
		int desvio = duracaoDoFilme < duracaoMinimaPreferida ? (duracaoMinimaPreferida - duracaoDoFilme) : (duracaoDoFilme - duracaoMaximaPreferida);
		double penalizacao = (desvio / (double) MINUTOS_LIMITE) * SCORE_MAXIMO;
		return Math.max(SCORE_MINIMO, SCORE_MAXIMO - penalizacao);
	}
	
	private double calcularScoreDaAfinidade(Filme filme, PerfilCinefilo perfil) {
		Map<Filme, Integer> notasFilmes = perfil.getNotasFilmes();
		if(notasFilmes.isEmpty()) {
			return SCORE_MINIMO;
		}
		boolean temAfinidadeAltaNoMesmoGenero = notasFilmes.entrySet().stream()
				.filter(nota -> nota.getValue() >= NOTA_ALTA)
				.anyMatch(genero -> compartilhaGenero(genero.getKey(), filme));
		return temAfinidadeAltaNoMesmoGenero ? BONUS_AFINIDADE : SCORE_MINIMO;
	}
	
	private boolean compartilhaGenero(Filme filmeJaAvaliado, Filme filmeCandidato) {
		return filmeJaAvaliado.getGeneros().stream()
				.anyMatch(genero -> filmeCandidato.getGeneros().contains(genero));
	}
	
	
}
