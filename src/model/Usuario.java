package model;

import exceptions.PerfilIncompletoException;

public class Usuario {
	
	private int id;
	private String nome;
	private int idade;
	private PerfilCinefilo perfil;
	private boolean notificacoesHabilitadas;
	
	public Usuario(int id, String nome, int idade, PerfilCinefilo perfil, boolean notificacoesHabilitadas) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		if(perfil == null) {
			throw new PerfilIncompletoException("O perfil não pode ser nulo");
		}
		this.perfil = perfil;
		this.notificacoesHabilitadas = notificacoesHabilitadas;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public PerfilCinefilo getPerfil() {
		return perfil;
	}

	public boolean isNotificacoesHabilitadas() {
		return notificacoesHabilitadas;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", idade=" + idade + "]";
	}
	
	
}
