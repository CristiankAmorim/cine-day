package model;

import java.util.List;
import java.util.Objects;

import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;

public class Filme {

	private String id;
	private String titulo;
	private int ano;
	private int duracaoMinutos;
	private List<Genero> generos;
	private ClassificacaoEtaria classificacao;
	private Idioma idioma;
	private int popularidade;
	
	public Filme(String id, String titulo, int ano, int duracaoMinutos, List<Genero> generos,
			ClassificacaoEtaria classificacao, Idioma idioma, int popularidade) {
		this.id = id;
		this.titulo = titulo;
		this.ano = ano;
		this.duracaoMinutos = duracaoMinutos;
		this.generos = generos;
		this.classificacao = classificacao;
		this.idioma = idioma;
		this.popularidade = popularidade;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAno() {
		return ano;
	}

	public int getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public ClassificacaoEtaria getClassificacao() {
		return classificacao;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public int getPopularidade() {
		return popularidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(generos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(generos, other.generos);
	}

	@Override
	public String toString() {
		return "Filme [id=" + id + ", titulo=" + titulo + ", popularidade=" + popularidade + "]";
	}
	
	
	
}
