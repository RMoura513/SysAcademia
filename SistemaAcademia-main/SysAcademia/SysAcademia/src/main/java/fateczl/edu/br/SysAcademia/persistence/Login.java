package fateczl.edu.br.SysAcademia.persistence;

import java.sql.SQLException;

public interface Login {

	public String fazerLogin(String usuario, String senha) throws SQLException, ClassNotFoundException;
}
