package fateczl.edu.br.SysAcademia.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fateczl.edu.br.SysAcademia.model.Aluno;
import fateczl.edu.br.SysAcademia.model.Atendente;
import fateczl.edu.br.SysAcademia.model.Personal;

@Repository
public class ComboBoxDAO implements IComboBoxDAO {

	@Autowired
	GenericDAO gDAO;
	
	@Override
	public List<Aluno> combobox_Alunos() throws SQLException, ClassNotFoundException{
		Connection c = gDAO.getConnection();
		List<Aluno> alunos = new ArrayList<>();
		String sql = "SELECT Nome FROM Aluno";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Aluno aluno = new Aluno();
			aluno.setNome(rs.getString("Nome"));
			alunos.add(aluno);
		}
		
		rs.close();
		ps.close();
		c.close();
		
		return alunos;
	}

	@Override
	public List<Personal> combobox_Personal() throws SQLException, ClassNotFoundException{
		Connection c = gDAO.getConnection();
		List<Personal> personais = new ArrayList<>();
		String sql = "SELECT Nome FROM Personal";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Personal personal = new Personal();
			personal.setNome(rs.getString("Nome"));
			personais.add(personal);
		}
		
		rs.close();
		ps.close();
		c.close();
		return personais;
	}

	@Override
	public List<Atendente> combobox_Atendente() throws SQLException, ClassNotFoundException{
		Connection c = gDAO.getConnection();
		List<Atendente> atendentes = new ArrayList<>();
		String sql = "SELECT Nome FROM Atendente";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Atendente atendente = new Atendente();
			atendente.setNome(rs.getString("Nome"));
			atendentes.add(atendente);
		}
		
		rs.close();
		ps.close();
		c.close();
		return atendentes;
	}

}
