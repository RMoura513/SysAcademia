package fateczl.edu.br.SysAcademia.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fateczl.edu.br.SysAcademia.model.Ficha;

@Repository
public class Personal_FichaDAO implements ICRUD<Ficha>{


	@Autowired
	GenericDAO gDAO;
	
	@Override
	public String cadastrar(Ficha f) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_criar_ficha (?, ?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, f.getNome_personal());
		cs.setString(2, f.getNome_aluno());
		cs.setString(3, f.getDescricao());
		cs.registerOutParameter(4, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(4);
		
		cs.close();
		c.close();
		
		return saida;
	}

	@Override
	public List<Ficha> lista() throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Ficha> fichas = new ArrayList<>();
		String sql = "SELECT f.Id_Ficha,\r\n"
				+ "          p.Nome AS nome_personal,\r\n"
				+ "          a.Nome AS nome_aluno,\r\n"
				+ "	         f.Descricao \r\n"
				+ "   FROM Ficha f, Personal p, Aluno a\r\n"
				+ "   WHERE f.id_Aluno = a.Id\r\n"
				+ "     AND f.Id_Personal = p.Id";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		Ficha ficha = new Ficha();
		ficha.setId_ficha(rs.getInt("Id_Ficha"));
		ficha.setNome_personal(rs.getString("nome_personal"));
		ficha.setNome_aluno(rs.getString("nome_aluno"));
		ficha.setDescricao(rs.getString("Descricao"));
		
		fichas.add(ficha);
			
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return fichas;
	}

	@Override
	public String atualizar(Ficha f) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_atualizar_ficha (?, ?, ?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, f.getId_ficha());
		cs.setString(2, f.getDescricao());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(3);
		
		cs.close();		
		c.close();
		
		return saida;
	}

	@Override
	public String excluir(Ficha f) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "{CALL sp_excluir_ficha (?, ?)}";
		System.out.println(f.getId_ficha());
		CallableStatement cs = c.prepareCall(sql);
		cs.setInt(1, f.getId_ficha());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(2);
		
		cs.close();
		c.close();
		return saida;
	}

	@Override
	public Ficha buscar(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		String sql = "SELECT f.Id_Ficha,\r\n"
				+ "       p.Nome AS nome_personal,\r\n"
				+ "       a.Nome AS nome_aluno,\r\n"
				+ "	      f.Descricao \r\n"
				+ "   FROM Ficha f, Personal p, Aluno a\r\n"
				+ "   WHERE f.id_Aluno = a.Id\r\n"
				+ "     AND f.Id_Personal = p.Id\r\n"
				+ "     AND f.Id_Ficha = ? ";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Ficha ficha = new Ficha();
		
		if(rs.next()) {
			
			ficha.setId_ficha(rs.getInt("Id_Ficha"));
			ficha.setNome_personal(rs.getString("nome_personal"));
			ficha.setNome_aluno(rs.getString("nome_aluno"));
			ficha.setDescricao(rs.getString("Descricao"));
		}
		
		ps.close();
		c.close();
		
		return ficha;
	}

	
		
	
}
