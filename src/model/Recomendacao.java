package model;

import model.enums.Genero;

public class Recomendacao {

	private Filme filme;
	private double score;
	private String justificativa;
	
	public Recomendacao(Filme filme, double score) {
		this.filme = filme;
		this.score = score;
		this.justificativa = gerarJustificativa(filme, score);
	}
	
	private static String gerarJustificativa(Filme filme, double score) {
		String generosTexto = filme.getGeneros().stream()
				.map(Genero::name)
				.reduce((a, b) -> a + " e " + b)
				.orElse("variados");
		return String.format(
				"Recomendamos '%s' porque você gosta de %s e ele tem score %.0f/100.",
				filme.getTitulo(), generosTexto, score);
	}

	public Filme getFilme() {
		return filme;
	}

	public double getScore() {
		return score;
	}

	public String getJustificativa() {
		return justificativa;
	}

	@Override
	public String toString() {
		return "Recomendacao [filme=" + filme.getTitulo() + ", score=" + score + "]";
	}
	
}
