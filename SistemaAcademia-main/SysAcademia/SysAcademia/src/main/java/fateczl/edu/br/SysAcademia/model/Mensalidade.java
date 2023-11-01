package fateczl.edu.br.SysAcademia.model;

import java.time.LocalDate;

public class Mensalidade {

	private int id_mensalidade;
	private String nome_aluno;
	private float valor;
	private LocalDate dataVenc;
	private String descricao;
	
	public int getId_mensalidade() {
		return id_mensalidade;
	}
	public void setId_mensalidade(int id_mensalidade) {
		this.id_mensalidade = id_mensalidade;
	}
	public String getNome_aluno() {
		return nome_aluno;
	}
	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public LocalDate getDataVenc() {
		return dataVenc;
	}
	public void setDataVenc(LocalDate dataVenc) {
		this.dataVenc = dataVenc;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Mensalidade [id_mensalidade=" + id_mensalidade + ", nome_aluno=" + nome_aluno + ", valor=" + valor
				+ ", dataVenc=" + dataVenc + ", descricao=" + descricao + "]";
	}
	

	
	
	
	
	
	
	
	
}
