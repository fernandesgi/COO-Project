package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import entidades.Livro;

public class LivroDao {

	final Collator comparadorDeStrings = Collator.getInstance();

	public LivroDao() {
		// Isto eh para ignorar os acentos nas comparacoes de Strings
		comparadorDeStrings.setStrength(Collator.NO_DECOMPOSITION);
	}

	public void adiciona(Livro livro) {
		if (livro == null) {
			throw new IllegalArgumentException("Parametro livro nao pode ser nulo.");
		}

		if (livro.getTitulo() == null) {
			throw new IllegalArgumentException("Nao e possivel adicionar um livro sem titulo.");
		}

		Connection connection = JDBCConnectionFactory.getConnection(); // entra no meu BD
		String sql = "insert into livros (titulo,autor,editora,idUsuario,emprestado) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setString(3, livro.getEditora());
			stmt.setInt(4, livro.getIdUsuario());
			stmt.setBoolean(5, false); // Nao esta emprestado pra ninguem
			stmt.execute(); // executar o comando
			stmt.close(); // fechar o comando
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}
	}


	public void atualiza(Livro livro) {
		if (livro == null) {
			throw new IllegalArgumentException("Par�metro livro n�o pode ser nulo.");
		}

		if (livro.getTitulo() == null) {
			throw new IllegalArgumentException("N�o � poss�vel atualizar um livro sem t�tulo.");
		}

		if (buscaPorTitulo(livro.getTitulo()) == null) {
			throw new IllegalArgumentException("N�o existe livro com o t�tulo\"" + livro.getTitulo() + "\" na base de dados.");
		}

		Connection connection = JDBCConnectionFactory.getConnection();
		String sql = "update livros set autor = ?, editora = ? where titulo = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, livro.getAutor());
			stmt.setString(2, livro.getEditora());
			stmt.setString(3, livro.getTitulo());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}


	public Livro buscaPorTitulo(String titulo) {
		if (titulo == null) {
			throw new IllegalArgumentException("Parametro titulo nao pode ser nulo.");
		}

		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		Livro livro = null;
		try {
			stmt = connection.prepareStatement("select * from livros where titulo=?");
			stmt.setString(1, titulo);
			ResultSet rs = stmt.executeQuery(); // retorna o ResultSet com o excecuteQuery

			if (rs.next()) { // assume que nao tem repeticao
				String title = rs.getString("titulo");
				String aut = rs.getString("autor");
				String edi = rs.getString("editora");
				int idUser = rs.getInt("idUsuario");
				livro = new Livro(aut, title, edi, idUser);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}

		return livro; // pode retornar o ResultSet ou Nulo
	}


	public void remove(Livro livro) {
		if (livro == null) {
			throw new IllegalArgumentException("Parametro livro nao pode ser nulo.");
		}

		if (livro.getTitulo() == null) {
			throw new IllegalArgumentException("Nao eh possivel remover um livro sem titulo.");
		}

		if (buscaPorTitulo(livro.getTitulo()) == null) {
			throw new IllegalArgumentException("Nao existe livro com o titulo\"" + livro.getTitulo() + "\" na base de dados.");
		}

		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("delete from livros where titulo=? and idUsuario =?");
			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2, livro.getIdUsuario());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}

	public List<Livro> listaTodosOrdenandoPorTitulo() {
		List<Livro> livros = listaTodos();

		Collections.sort(livros, new Comparator<Livro>() {

			@Override
			public int compare(Livro o1, Livro o2) {
				return comparadorDeStrings.compare(o1.getTitulo(), o2.getTitulo());
			}
		});

		return livros;
	}

	public List<Livro> listaTodosOrdenandoPorAutor() {
		List<Livro> livros = listaTodos();

		Collections.sort(livros, new Comparator<Livro>() {

			@Override
			public int compare(Livro o1, Livro o2) {
				return comparadorDeStrings.compare(o1.getAutor(), o2.getAutor());
			}
		});

		return livros;
	}

	public List<Livro> listaTodos(){
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		LinkedList<Livro> livros = new LinkedList<Livro>();
		Livro livro = null;
		try {
			stmt = connection.prepareStatement("select * from livros");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString("titulo");
				String aut = rs.getString("autor");
				String edi = rs.getString("editora");
				int idUser = rs.getInt("idUsuario");
				livro = new Livro(aut, title, edi, idUser);
				livros.add(livro);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}

		return livros;
	}
	
	// Lista todos Usuarios que possuem o livro 
	public List<Integer> listaTodosPorUsuario(Livro livro){
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		LinkedList<Integer> usuarios = new LinkedList<Integer>();
		try {
			stmt = connection.prepareStatement("select * from livros where titulo = '" + livro.getTitulo()+"'");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int idUser = rs.getInt("idUsuario");
				usuarios.add(idUser);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}

		return usuarios;
	}
	
}
