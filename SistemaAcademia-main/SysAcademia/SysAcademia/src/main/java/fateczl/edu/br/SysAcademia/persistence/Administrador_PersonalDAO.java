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

import fateczl.edu.br.SysAcademia.model.Personal;

@Repository
public class Administrador_PersonalDAO implements ICRUD<Personal> {

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public String cadastrar(Personal p) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_cadastrar_personal (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";	
		
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = p.getDataNasc().format(dtf);
	    
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, p.getNome());
		cs.setString(2, p.getFormacao());
		cs.setString(3, p.getTipoFormacao());	
		cs.setString(4, string_data);
		cs.setString(5, p.getCep());
		cs.setString(6, p.getLogradouro());
		cs.setInt(7, p.getNumero_end());
		cs.setString(8, p.getUsuario());
		cs.setString(9, p.getSenha());
		cs.registerOutParameter(10, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(10);
			
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public List<Personal> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Personal> lista_per = new ArrayList<>();
		String sql = "SELECT Id, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha, Formacao, TipoFormacao FROM Personal";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Personal per = new Personal();
			per.setId(rs.getInt("Id"));
			per.setNome(rs.getString("Nome"));
			per.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			per.setCep(rs.getString("CEP"));
			per.setLogradouro(rs.getString("Logradouro"));
			per.setNumero_end(rs.getInt("Numero"));
			per.setUsuario(rs.getString("Usuario"));
			per.setSenha(rs.getString("Senha"));
			per.setFormacao(rs.getString("Formacao"));
			per.setTipoFormacao(rs.getString("TipoFormacao"));
			
			lista_per.add(per);
		}
		rs.close();
		ps.close();
		c.close();
		
		return lista_per;
	}

	@Override
	public String atualizar(Personal p) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_atualiza_personal (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	     
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = p.getDataNasc().format(dtf);
		
		
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, p.getId());
		cs.setString(2, p.getFormacao());
		cs.setString(3,  p.getTipoFormacao());
		cs.setString(4, p.getNome());
		cs.setString(5, string_data);
		cs.setString(6, p.getCep());
		cs.setString(7, p.getLogradouro());
		cs.setInt(8, p.getNumero_end());
		cs.setString(9, p.getUsuario());
		cs.setString(10, p.getSenha());
        cs.registerOutParameter(11, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(11);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public String excluir(Personal p) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_excluir_personal (?, ?)}";
		
        CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, p.getId());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(2);
		
		cs.close();
		c.close();
		return saida;

	}

	@Override
	public Personal buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT Id, Nome, DataNasc, CEP, Logradouro, Numero, Usuario, Senha, Formacao, TipoFormacao"
				+ " FROM Personal WHERE Id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Personal per = new Personal();
		
		if(rs.next()) {
			
			per.setId(rs.getInt("Id"));
			per.setNome(rs.getString("Nome"));
			per.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			per.setCep(rs.getString("CEP"));
			per.setLogradouro(rs.getString("Logradouro"));
			per.setNumero_end(rs.getInt("Numero"));
			per.setUsuario(rs.getString("Usuario"));
			per.setSenha(rs.getString("Senha"));			
			per.setFormacao(rs.getString("Formacao"));
			per.setTipoFormacao(rs.getString("TipoFormacao"));
		}
		
		return per;
	}

}
