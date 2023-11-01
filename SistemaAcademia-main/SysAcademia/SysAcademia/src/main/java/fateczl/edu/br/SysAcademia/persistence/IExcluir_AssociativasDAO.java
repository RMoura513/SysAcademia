package fateczl.edu.br.SysAcademia.persistence;

import java.sql.SQLException;

public interface IExcluir_AssociativasDAO {
	
	public void excluir_personal_aula(int id) throws SQLException, ClassNotFoundException;
	public void excluir_personal_ficha(int id) throws SQLException, ClassNotFoundException;
	public void excluir_aluno_aula(int id) throws SQLException, ClassNotFoundException;
	public void excluir_aluno_ficha(int id) throws SQLException, ClassNotFoundException;
	public void excluir_aluno_mensalidade(int id) throws SQLException, ClassNotFoundException;		

}
