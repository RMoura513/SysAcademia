package fateczl.edu.br.SysAcademia.model;

import java.time.LocalTime;

public class Aula {
	
	private int id;
	private String nome_personal;
	private String nome_aluno;
	private String nome;
	private LocalTime duracao;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_personal() {
		return nome_personal;
	}
	public void setNome_personal(String nome_personal) {
		this.nome_personal = nome_personal;
	}
	public String getNome_aluno() {
		return nome_aluno;
	}
	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalTime getDuracao() {
		return duracao;
	}
	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}
	@Override
	public String toString() {
		return "Aula [id=" + id + ", nome_personal=" + nome_personal + ", nome_aluno=" + nome_aluno + ", nome=" + nome
				+ ", duracao=" + duracao + "]";
	}

	
	
	
	
}
