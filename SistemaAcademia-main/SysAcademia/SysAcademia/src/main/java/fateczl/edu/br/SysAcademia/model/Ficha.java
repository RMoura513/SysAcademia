package fateczl.edu.br.SysAcademia.model;

public class Ficha {

	
	private int id_ficha;
	private String nome_aluno;
	private String nome_personal;
	private String descricao;
	
	public int getId_ficha() {
		return id_ficha;
	}
	public void setId_ficha(int id_ficha) {
		this.id_ficha = id_ficha;
	}
	public String getNome_aluno() {
		return nome_aluno;
	}
	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}
	public String getNome_personal() {
		return nome_personal;
	}
	public void setNome_personal(String nome_personal) {
		this.nome_personal = nome_personal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Ficha [id_ficha=" + id_ficha + ", nome_aluno=" + nome_aluno + ", nome_personal=" + nome_personal
				+ ", descricao=" + descricao + "]";
	}
	

	
	
	
}
