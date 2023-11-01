package fateczl.edu.br.SysAcademia.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FazerLogin implements Login {

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public String fazerLogin(String usuario, String senha) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_realizar_login(?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, usuario);
		cs.setString(2, senha);
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		
		String acesso = cs.getString(3);
		
		cs.close();
		c.close();
		return acesso;
	}

}
