package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.DuracaoInvalidaException;
import exceptions.NotaInvalidaException;
import exceptions.PesoInvalidoException;
import model.enums.ClassificacaoEtaria;
import model.enums.Genero;
import model.enums.Idioma;
/**
 * Características dos filmes que agradam um usuário
 */
public class PerfilCinefilo {
	
	private static final double PESO_MINIMO_GENERO = 0.0;
	private static final double PESO_MAXIMO_GENERO = 1.0;
	private static final int NOTA_MINIMA_FILME = 1;
	private static final int NOTA_MAXIMA_FILME = 5;
	
	private final Map<Genero, Double> pesoDoGenero;
	private int duracaoMinimaMinutos;
    private int duracaoMaximaMinutos;
    private ClassificacaoEtaria classificacaoMaxima;
    private final List<Idioma> idiomasAceitos;
    private final Set<Filme> historicoFilmesAssistidos;
    private final Map<Filme, Integer> notasFilmes;
	
	public PerfilCinefilo() {
		this.pesoDoGenero = new EnumMap<>(Genero.class);
		this.idiomasAceitos = new ArrayList<>();
		this.historicoFilmesAssistidos = new HashSet<>();
		this.notasFilmes = new HashMap<>();
		this.duracaoMinimaMinutos = 0;
		this.duracaoMaximaMinutos = Integer.MAX_VALUE;
		this.classificacaoMaxima = ClassificacaoEtaria.DEZOITO;
	}
	
	/**
	 * Define o peso de preferência de um gênero.
	 * @param genero
	 * @param peso
	 * @throws PesoInvalidoException
	 */
	public void inserirPesoDoGenero(Genero genero, double peso) {
		if(peso < PESO_MINIMO_GENERO || peso > PESO_MAXIMO_GENERO) {
			throw new PesoInvalidoException("Peso iválido. O peso deve ser entre " + PESO_MINIMO_GENERO + " e " + PESO_MAXIMO_GENERO);
		}
		pesoDoGenero.put(genero, peso);
	}
	
	/**
	 * Escolhe a faixa de duração ideal de um filme.
	 * @param duracaoMinima
	 * @param duracaoMaxima
	 */
	public void inserirFaixaDeDuracao(int duracaoMinima, int duracaoMaxima) {
		if(duracaoMinima > duracaoMaxima) {
			throw new DuracaoInvalidaException("Duração de minutos inválida (" + duracaoMinima + "). A duração mínima não pode ser maior que a máxima (" + duracaoMaxima + ").");
		}
		this.duracaoMinimaMinutos = duracaoMinima;
		this.duracaoMaximaMinutos = duracaoMaxima;
	}
	
	/**
	 * Atribuir nota a um filme.
	 * @param idFilme
	 * @param nota
	 */
	public void atribuirNotaAoFilme(Filme filme, int nota) {
		if(nota < NOTA_MINIMA_FILME || nota > NOTA_MAXIMA_FILME) {
			throw new NotaInvalidaException("Nota inválida. A nota deve ser entre " + NOTA_MINIMA_FILME + " e " + NOTA_MAXIMA_FILME);
		}
		notasFilmes.put(filme, nota);
	}
	
	/**
	 * Armazena um filme já assistido em uma lista.
	 * @param idFilme
	 */
	public void marcarFilmeComoAssistido(Filme filme) {
		this.historicoFilmesAssistidos.add(filme);
	}
	
	/**
	 * Armazena um idioma de preferência do usuário em uma lista.
	 * @param idioma
	 */
	public void adicinarIdioma(Idioma idioma) {
		this.idiomasAceitos.add(idioma);
	}
	
	/**
	 * Verifica se um filme já está na lista de assistidos.
	 * @param idFilme
	 * @return
	 */
	public boolean filmeJaAssistido(Filme filme) {
		return this.historicoFilmesAssistidos.contains(filme);
	}
	
	/**
	 * Consulta a nota dada a um filme.
	 * @param idFilme
	 * @return
	 */
	public int verificarNotaDoFilme(Filme filme) {
		return this.notasFilmes.get(filme);
	}

	public Map<Genero, Double> getPesoDoGenero() {
		return pesoDoGenero;
	}
	
	public double getPesoDoGenero(Genero genero) {
		return pesoDoGenero.getOrDefault(genero, 0.5);
	}

	public int getDuracaoMinimaMinutos() {
		return duracaoMinimaMinutos;
	}

	public int getDuracaoMaximaMinutos() {
		return duracaoMaximaMinutos;
	}

	public ClassificacaoEtaria getClassificacaoMaxima() {
		return classificacaoMaxima;
	}

	public List<Idioma> getIdiomasAceitos() {
		return idiomasAceitos;
	}

	public Set<Filme> getHistoricoFilmesAssistidos() {
		return historicoFilmesAssistidos;
	}

	public Map<Filme, Integer> getNotasFilmes() {
		return notasFilmes;
	}

	public void setClassificacaoMaxima(ClassificacaoEtaria classificacaoMaxima) {
		this.classificacaoMaxima = classificacaoMaxima;
	}
	
	
}
