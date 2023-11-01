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

import fateczl.edu.br.SysAcademia.model.Mensalidade;

@Repository
public class Atendente_MensalidadeDAO implements ICRUD<Mensalidade>{

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public String cadastrar(Mensalidade m) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_cria_mensalidade (?, ?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
	    String string_data = m.getDataVenc().format(dtf);
		
		cs.setString(1, m.getNome_aluno());
		cs.setFloat(2, m.getValor());
		cs.setString(3, string_data);
		cs.setString(4, m.getDescricao());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public List<Mensalidade> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Mensalidade> mensalidades = new ArrayList<>();
		String sql = "SELECT m.Id_Mensalidade,\r\n"
				+ "          a.Nome AS nome_aluno,\r\n"
				+ "          m.Descricao,\r\n"
				+ "	         m.Valor,\r\n"
				+ "	         m.DataVenc\r\n"
				+ "   FROM Mensalidade m, Aluno a\r\n"
				+ "   WHERE m.Id_Aluno = a.Id";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Mensalidade mens = new Mensalidade();
			mens.setId_mensalidade(rs.getInt("Id_Mensalidade"));
			mens.setNome_aluno(rs.getString("nome_aluno"));
			mens.setDescricao(rs.getString("Descricao"));
			mens.setValor(rs.getFloat("Valor"));
			mens.setDataVenc(LocalDate.parse(rs.getString("DataVenc")));
			mensalidades.add(mens);
			
			
		}
		rs.close();
		ps.close();
		c.close();
		
		return mensalidades;
	}

	@Override
	public String atualizar(Mensalidade m) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_atualizar_mensalidade (?, ?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, m.getId_mensalidade());
		cs.setFloat(2, m.getValor());
		cs.setString(3, m.getDataVenc().toString());
		cs.setString(4, m.getDescricao());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public String excluir(Mensalidade m) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_excluir_mensalidade (?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, m.getId_mensalidade());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(2);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public Mensalidade buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT m.Id_Mensalidade,\r\n"
				+ "          a.Nome AS nome_aluno,\r\n"
				+ "          m.Descricao,\r\n"
				+ "	         m.Valor,\r\n"
				+ "	         m.DataVenc\r\n"
				+ "FROM Mensalidade m, Aluno a\r\n"
				+ "WHERE m.Id_Aluno = a.Id\r\n"
				+ "  AND m.Id_Mensalidade = ?";
	 
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Mensalidade mensalidade = new Mensalidade();
		
		if(rs.next()) {
		mensalidade.setId_mensalidade(rs.getInt("Id_Mensalidade"));
		mensalidade.setNome_aluno(rs.getString("nome_aluno"));
		mensalidade.setDescricao(rs.getString("Descricao"));
		mensalidade.setValor(rs.getFloat("Valor"));
		mensalidade.setDataVenc(LocalDate.parse(rs.getString("DataVenc")));
		}
		
		
		rs.close();
		ps.close();
		c.close();
		
		return mensalidade;
	}

}
