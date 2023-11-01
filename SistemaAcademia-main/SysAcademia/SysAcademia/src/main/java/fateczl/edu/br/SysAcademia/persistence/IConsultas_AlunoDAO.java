package fateczl.edu.br.SysAcademia.persistence;

import java.sql.SQLException;
import java.util.List;

import fateczl.edu.br.SysAcademia.model.Ficha;
import fateczl.edu.br.SysAcademia.model.Mensalidade;

public interface IConsultas_AlunoDAO {
	
	public List<Ficha> consultarFichaDeTreino(int id) throws SQLException, ClassNotFoundException;
	public List<Mensalidade> consultarMensalidade(int id) throws SQLException, ClassNotFoundException;
}
