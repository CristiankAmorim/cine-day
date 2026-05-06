package model.enums;
/**
 * Classificação indicativa dos filmes.
 */
public enum ClassificacaoEtaria {
	LIVRE(0), DEZ(10), DOZE(12), QUATORZE(14), DEZESSEIS(16), DEZOITO(18);

	private final int idade;
	
	ClassificacaoEtaria(int idade) {
		this.idade = idade;
	}

	public int getIdade() {
		return idade;
	}
	
	/**
	 * Método para verificar se o filme está liberado para idade do usuário
	 * @param idadeDoUsuario
	 * @return
	 */
	public boolean liberadoParaIdade(int idadeDoUsuario) {
		return idadeDoUsuario >= this.idade;
	}
	
	/**
	 * Método para verificar se a classificação está dentro da máxima
	 * @param maxima
	 * @return
	 */
	public boolean dentroDaClassificacaoMaxima(ClassificacaoEtaria classificacao) {
		return this.idade <= classificacao.idade;
	}
	
}
