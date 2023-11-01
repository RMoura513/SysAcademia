package fateczl.edu.br.SysAcademia.model;

import java.time.LocalDate;

public abstract class Funcionario {

	 String nome;
	 String usuario;
	 String senha;
	 LocalDate dataNasc;
	 String cep;
	 String logradouro;
	 int numero_end;
	 
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		return "Funcionario [nome=" + nome + ", usuario=" + usuario + ", senha=" + senha + ", dataNasc=" + dataNasc
				+ ", cep=" + cep + ", logradouro=" + logradouro + ", numero_end=" + numero_end + "]";
	}	
	 
	 
}
