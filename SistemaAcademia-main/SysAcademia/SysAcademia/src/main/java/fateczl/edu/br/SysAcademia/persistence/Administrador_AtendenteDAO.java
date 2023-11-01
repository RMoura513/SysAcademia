package fateczl.edu.br.SysAcademia.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fateczl.edu.br.SysAcademia.model.Atendente;

@Repository
public class Administrador_AtendenteDAO implements ICRUD<Atendente> {
	
	@Autowired
	GenericDAO gDAO;
	
	public String cadastrar(Atendente a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_cadastrar_atendente (?, ?, ?, ?, ?, ?, ?, ?)}";
		
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = a.getDataNasc().format(dtf);
	    
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, a.getNome());	
		cs.setString(2, string_data);
		cs.setString(3, a.getCep());
		cs.setString(4, a.getLogradouro());
		cs.setInt(5, a.getNumero_end());
		cs.setString(6, a.getUsuario());
		cs.setString(7, a.getSenha());
		cs.registerOutParameter(8, Types.VARCHAR);
		cs.execute();
			
		String saida = cs.getString(8);
		
		cs.close();
		c.close();
		
		return saida;
	}
	
	
	@Override
	public List<Atendente> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Atendente> lista_aten = new ArrayList<>();
		String sql = "SELECT Id, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Atendente";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Atendente aten = new Atendente();
			aten.setId(rs.getInt("Id"));
			aten.setNome(rs.getString("Nome"));
			aten.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			aten.setCep(rs.getString("CEP"));
			aten.setLogradouro(rs.getString("Logradouro"));
			aten.setNumero_end(rs.getInt("Numero"));
			aten.setUsuario(rs.getString("Usuario"));
			aten.setSenha(rs.getString("Senha"));
			
			lista_aten.add(aten);
		}
		
		
		c.close();
		return lista_aten;
	}
	

	
	@Override
	public String atualizar(Atendente a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_atualiza_atendente (?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	      String string_data = a.getDataNasc().format(dtf);
		
		
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, a.getId());
		cs.setString(2, a.getNome());
		cs.setString(3, string_data);
		cs.setString(4, a.getCep());
		cs.setString(5, a.getLogradouro());
		cs.setInt(6, a.getNumero_end());
		cs.setString(7, a.getUsuario());
		cs.setString(8, a.getSenha());
		cs.registerOutParameter(9, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(9);
		
		
		cs.close();
		c.close();
		
		return saida;
	}
	
	
	@Override
	public String excluir(Atendente a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_excluir_atendente (?, ?)}";
		
        CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, a.getId());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(2);
		
		cs.close();
		c.close();
		return saida;
	}
	

	
	@Override
	public Atendente buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT Id, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha FROM Atendente WHERE Id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Atendente aten = new Atendente();
		
		if(rs.next()) {
			
			aten.setId(rs.getInt("Id"));
			aten.setNome(rs.getString("Nome"));
			aten.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			aten.setCep(rs.getString("CEP"));
			aten.setLogradouro(rs.getString("Logradouro"));
			aten.setNumero_end(rs.getInt("Numero"));
			aten.setUsuario(rs.getString("Usuario"));
			aten.setSenha(rs.getString("Senha"));			
		}
		
		return aten;
	}
	

	
}

