package model;

public class Recomendacao {

	private Filme filme;
	private double score;
	private String justificativa;
	
	public Recomendacao(Filme filme, double score, String justificativa) {
		this.filme = filme;
		this.score = score;
		this.justificativa = justificativa;
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
