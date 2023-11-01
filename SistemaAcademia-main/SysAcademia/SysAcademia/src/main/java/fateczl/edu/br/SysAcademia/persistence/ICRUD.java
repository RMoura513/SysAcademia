package fateczl.edu.br.SysAcademia.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICRUD<T> {
	
	public String cadastrar(T t) throws SQLException, ClassNotFoundException;
	public List<T> lista() throws SQLException, ClassNotFoundException;
	public String atualizar(T t) throws SQLException, ClassNotFoundException;
	public String excluir(T t) throws SQLException, ClassNotFoundException;
	public T buscar(int id) throws SQLException, ClassNotFoundException;
}
