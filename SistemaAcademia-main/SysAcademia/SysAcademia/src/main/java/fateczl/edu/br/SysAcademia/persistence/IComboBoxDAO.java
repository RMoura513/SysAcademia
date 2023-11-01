package fateczl.edu.br.SysAcademia.persistence;

import java.sql.SQLException;
import java.util.List;

import fateczl.edu.br.SysAcademia.model.Aluno;
import fateczl.edu.br.SysAcademia.model.Atendente;
import fateczl.edu.br.SysAcademia.model.Personal;

public interface IComboBoxDAO {
	
	public List<Aluno> combobox_Alunos() throws SQLException, ClassNotFoundException;
	public List<Personal> combobox_Personal() throws SQLException, ClassNotFoundException;
	public List<Atendente> combobox_Atendente() throws SQLException, ClassNotFoundException;

}
