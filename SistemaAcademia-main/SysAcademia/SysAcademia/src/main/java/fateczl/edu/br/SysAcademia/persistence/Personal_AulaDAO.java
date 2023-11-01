package fateczl.edu.br.SysAcademia.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fateczl.edu.br.SysAcademia.model.Aula;

@Repository
public class Personal_AulaDAO implements ICRUD<Aula>{

	@Autowired
	GenericDAO gDAO;
	
	
	@Override
	public String cadastrar(Aula a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_criar_aula (?, ?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, a.getNome_personal());
		cs.setString(2, a.getNome_aluno());
		cs.setString(3, a.getNome());
		cs.setString(4, a.getDuracao().toString());
		cs.registerOutParameter(5, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(5);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public List<Aula> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Aula> aulas = new ArrayList<>();
		String sql = "SELECT au.Id_Aula,\r\n"
				+ "          al.Nome AS nome_aluno,\r\n"
				+ "	         p.Nome AS nome_personal,\r\n"
				+ "          au.Nome AS nome_aula,\r\n"
				+ "          au.Duracao\r\n"
				+ "   FROM Aula au, aluno al, Personal p\r\n"
				+ "   WHERE au.Id_Aluno = al.Id\r\n"
				+ "     AND au.Id_Personal = p.Id";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Aula aula = new Aula();
			aula.setId(rs.getInt("Id_Aula"));
			aula.setNome_aluno(rs.getString("nome_aluno"));
			aula.setNome_personal(rs.getString("nome_personal"));
			aula.setNome(rs.getString("nome_aula"));
			aula.setDuracao(LocalTime.parse(rs.getString("duracao")));
			aulas.add(aula);
		}
		
		ps.close();
		rs.close();
		c.close();
		
		return aulas;
	}

	@Override
	public String atualizar(Aula a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_atualizar_aula (?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, a.getId());
		cs.setString(2, a.getNome());
		cs.setString(3, a.getDuracao().toString());
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(4);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public String excluir(Aula a) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_excluir_aula (?, ?)}";
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
	public Aula buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT au.Id_Aula,\r\n"
				+ "          al.Nome AS nome_aluno,\r\n"
				+ "	         p.Nome AS nome_personal,\r\n"
				+ "          au.Nome AS nome_aula,\r\n"
				+ "          au.Duracao\r\n"
				+ "  FROM Aula au, aluno al, Personal p\r\n"
				+ "  WHERE au.Id_Aluno = al.Id\r\n"
				+ "    AND au.Id_Personal = p.Id\r\n"
				+ "    AND au.Id_Aula = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Aula aula = new Aula();
		if(rs.next()) {
		aula.setId(rs.getInt("Id_Aula"));
		aula.setNome_aluno(rs.getString("nome_aluno"));
		aula.setNome_personal(rs.getString("nome_personal"));
		aula.setNome(rs.getString("nome_aula"));
		aula.setDuracao(LocalTime.parse(rs.getString("Duracao")));
		}
		
		return aula;
	}

}
