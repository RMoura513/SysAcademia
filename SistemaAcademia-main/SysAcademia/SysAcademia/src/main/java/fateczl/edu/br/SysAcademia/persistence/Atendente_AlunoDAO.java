package fateczl.edu.br.SysAcademia.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import fateczl.edu.br.SysAcademia.model.Aluno;

@Repository
public class Atendente_AlunoDAO implements ICRUD<Aluno>{
	
	@Autowired
	GenericDAO gDAO;

	@Override
	public String cadastrar(Aluno a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "INSERT INTO Aluno (Nome, DataNasc, CPF, CEP, Logradouro, Numero_end, Usuario, Senha)\r\n"
				   + "VALUES               (?, ?, ?, ?, ?, ?, ?, ?)";	
		
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = a.getDataNasc().format(dtf);
	    
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getNome());	
		ps.setString(2, string_data);
		ps.setString(3, a.getCpf());
		ps.setString(4, a.getCep());
		ps.setString(5, a.getLogradouro());
		ps.setInt(6, a.getNumero_end());
		ps.setString(7, a.getUsuario());
		ps.setString(8, a.getSenha());
		ps.execute();
			
		
		ps.close();
		c.close();
		
		return "Aluno Cadastrado";
	}
	

	@Override
	public List<Aluno> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Aluno> lista_alu = new ArrayList<>();
		String sql = "SELECT Id, Nome, DataNasc, CPF, CEP, Logradouro, Numero_end, Usuario, Senha FROM Aluno";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Aluno alu = new Aluno();
			alu.setId(rs.getInt("Id"));
			alu.setNome(rs.getString("Nome"));
			alu.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			alu.setCpf(rs.getString("CPF"));
			alu.setCep(rs.getString("CEP"));
			alu.setLogradouro(rs.getString("Logradouro"));
			alu.setNumero_end(rs.getInt("Numero_end"));
			alu.setUsuario(rs.getString("Usuario"));
			alu.setSenha(rs.getString("Senha"));
			
			lista_alu.add(alu);
		}
		
		
		c.close();
		return lista_alu;
	}
	

	
	
	

	@Override
	public String atualizar(Aluno a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "UPDATE Aluno\r\n"
				      + "SET Nome = ?,\r\n"
			          + "    DataNasc = ?,\r\n"
				      + "    CPF = ?,\r\n"
				      + "	 CEP = ?,\r\n"
				      + "	 Logradouro = ?,\r\n"
				      + "	 Numero_end = ?,\r\n"
				      + "	 Usuario = ?,\r\n"
				      + "	 Senha = ?\r\n"
				      + "WHERE Id = ?";
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	      String string_data = a.getDataNasc().format(dtf);
		
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, a.getNome());
		ps.setString(2, string_data);
		ps.setString(3, a.getCpf());
		ps.setString(4, a.getCep());
		ps.setString(5, a.getLogradouro());
		ps.setInt(6, a.getNumero_end());
		ps.setString(7, a.getUsuario());
		ps.setString(8, a.getSenha());
		ps.setInt(9, a.getId());
		
		ps.execute();
		
		ps.close();
		c.close();
		
		return "Aluno Atualizado";
	}
	

	

	@Override
	public String excluir(Aluno a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "DELETE Aluno\r\n"
				   + "WHERE Id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, a.getId());
		ps.execute();
		
		ps.close();
		c.close();
		return "Aluno Excluido";
	}
	
	

	@Override
	public Aluno buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT Id, Nome, DataNasc, CPF, CEP, Logradouro, Numero_end, Usuario, Senha FROM Aluno WHERE Id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Aluno alu = new Aluno();
		
		if(rs.next()) {
			
			alu.setId(rs.getInt("Id"));
			alu.setNome(rs.getString("Nome"));
			alu.setDataNasc(LocalDate.parse(rs.getString("DataNasc")));
			alu.setCpf(rs.getString("CPF"));
			alu.setCep(rs.getString("CEP"));
			alu.setLogradouro(rs.getString("Logradouro"));
			alu.setNumero_end(rs.getInt("Numero_end"));
			alu.setUsuario(rs.getString("Usuario"));
			alu.setSenha(rs.getString("Senha"));			
		}
		
		return alu;
	}
	

	
}