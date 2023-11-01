package fateczl.edu.br.SysAcademia.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fateczl.edu.br.SysAcademia.model.Ficha;
import fateczl.edu.br.SysAcademia.model.Mensalidade;

@Repository
public class Consultas_AlunoDAO implements IConsultas_AlunoDAO{

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public List<Ficha> consultarFichaDeTreino(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Ficha> fichas = new ArrayList<>();
		String sql = "SELECT f.Id_Ficha,\r\n"
				+ "          p.Nome AS nome_personal,\r\n"
				+ "          a.Nome AS nome_aluno,\r\n"
				+ "	         f.Descricao\r\n"
				+ "   FROM Ficha f, Aluno a, Personal p\r\n"
				+ "   WHERE f.Id_Aluno = a.Id\r\n"
				+ "      AND f.Id_Personal = p.Id\r\n"
				+ "      AND a.Id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
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
	public List<Mensalidade> consultarMensalidade(int id) throws SQLException, ClassNotFoundException {
		Connection c = gDAO.getConnection();
		List<Mensalidade> mensalidades = new ArrayList<>();
		String sql = "SELECT m.Descricao,\r\n"
				+ "          m.Valor,\r\n"
				+ "	         m.DataVenc\r\n"
				+ "   FROM Mensalidade m, Aluno a\r\n"
				+ "   WHERE m.Id_Aluno = a.Id\r\n"
				+ "     AND a.Id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
		Mensalidade mensalidade = new Mensalidade();
		mensalidade.setDescricao(rs.getString("Descricao"));
		mensalidade.setValor(rs.getFloat("Valor"));
		mensalidade.setDataVenc(LocalDate.parse(rs.getString("DataVenc")));

		mensalidades.add(mensalidade);
		}
		
		c.close();
		return mensalidades;
	}

}
