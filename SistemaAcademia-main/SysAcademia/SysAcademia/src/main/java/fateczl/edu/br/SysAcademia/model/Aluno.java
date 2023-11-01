package fateczl.edu.br.SysAcademia.model;

import java.time.LocalDate;

public class Aluno {
	
	private int id;
	private String usuario;
	private String senha;
	private String cpf;
	private String nome;
	private LocalDate dataNasc;
	private String cep;
	private String logradouro;
	private int numero_end;
	
	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getUsuario() {
		return usuario;
	}




	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}




	public String getSenha() {
		return senha;
	}




	public void setSenha(String senha) {
		this.senha = senha;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public LocalDate getDataNasc() {
		return dataNasc;
	}




	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}




	public String getCep() {
		return cep;
	}




	public void setCep(String cep) {
		this.cep = cep;
	}




	public String getLogradouro() {
		return logradouro;
	}




	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}




	public int getNumero_end() {
		return numero_end;
	}




	public void setNumero_end(int numero_end) {
		this.numero_end = numero_end;
	}




	@Override
	public String toString() {
		return "aluno [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", cpf=" + cpf + ", nome=" + nome
				+ ", dataNasc=" + dataNasc + ", cep=" + cep + ", logradouro=" + logradouro + ", numero_end="
				+ numero_end + "]";
	}



}
