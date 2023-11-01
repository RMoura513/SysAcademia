package fateczl.edu.br.SysAcademia.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Excluir_AssocitivasDAO implements IExcluir_AssociativasDAO{

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public void excluir_personal_aula(int id) throws SQLException, ClassNotFoundException {
	    Connection c = gDAO.getConnection();
	    String sql = "DELETE Aula"
	    		+ "   WHERE Id_Personal = ?";
	    PreparedStatement ps = c.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.execute();
	    
	    ps.close();
	    c.close();	
	}

	@Override
	public void excluir_personal_ficha(int id) throws SQLException, ClassNotFoundException {
	    Connection c = gDAO.getConnection();
	    String sql = "DELETE Ficha"
	    		+ "   WHERE Id_Personal = ?";
	    PreparedStatement ps = c.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.execute();
	    
	    ps.close();
	    c.close();		
	}

	@Override
	public void excluir_aluno_aula(int id) throws SQLException, ClassNotFoundException {
	    Connection c = gDAO.getConnection();
	    String sql = "DELETE Aula"
	    		+ "   WHERE Id_Aluno = ?";
	    PreparedStatement ps = c.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.execute();
	    
	    ps.close();
	    c.close();	
	}

	@Override
	public void excluir_aluno_ficha(int id) throws SQLException, ClassNotFoundException {
	    Connection c = gDAO.getConnection();
	    String sql = "DELETE Aula"
	    		+ "   WHERE Id_Aluno = ?";
	    PreparedStatement ps = c.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.execute();
	    
	    ps.close();
	    c.close();	
	}

	@Override
	public void excluir_aluno_mensalidade(int id) throws SQLException, ClassNotFoundException {
	    Connection c = gDAO.getConnection();
	    String sql = "DELETE Mensalidade"
	    		+ "   WHERE Id_Aluno = ?";
	    PreparedStatement ps = c.prepareStatement(sql);
	    ps.setInt(1, id);
	    ps.execute();
	    
	    ps.close();
	    c.close();	
	}

}
