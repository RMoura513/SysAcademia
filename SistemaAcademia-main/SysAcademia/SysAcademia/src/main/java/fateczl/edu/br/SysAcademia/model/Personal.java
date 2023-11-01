package fateczl.edu.br.SysAcademia.model;


public class Personal extends Funcionario{
	
	private int id;
	private String formacao;
	private String tipoFormacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}
	public String getTipoFormacao() {
		return tipoFormacao;
	}
	public void setTipoFormacao(String tipoFormacao) {
		this.tipoFormacao = tipoFormacao;
	}
	
	
}
