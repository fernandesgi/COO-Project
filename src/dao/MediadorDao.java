package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import entidades.Livro;
import entidades.Usuario;

public class MediadorDao {
	
	public void adiciona(Livro livro, Integer i, Usuario usuario) {
		Connection connection = JDBCConnectionFactory.getConnection(); // entra no meu BD
		if (usuario.getIdUsuario() == i) {
			throw new IllegalArgumentException("Você ja possui esse livro");
		}
		String sql = "insert into solicitacoes (idSolicitante,idSolicitado,titulo,autor) values (?,?,?,?)";
		try {
			// Faz a solicitacao apenas pra quem n tinha enviado ainda
			PreparedStatement s = connection.prepareStatement("select * from solicitacoes where idSolicitante=? and idSolicitado=? and titulo=?");
			s.setInt(1, usuario.getIdUsuario());
			s.setInt(2, i);
			s.setString(3,livro.getTitulo());
			ResultSet rs = s.executeQuery();
			if (rs.next()){
				s.close();
				rs.close();
				return;
			}
			s.close();
			rs.close();
			
			// Faz a solicitacao pela primeira vez
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			stmt.setInt(2, i);
			stmt.setString(3, livro.getTitulo());
			stmt.setString(4, livro.getAutor());
			stmt.execute(); // executar o comando
			stmt.close(); // fechar o comando
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}
	}
	
	public void remove(Usuario usuario, Livro livro){
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("delete from solicitacoes where idSolicitante=? and titulo =?");
			stmt.setInt(1, usuario.getIdUsuario());
			stmt.setString(2, livro.getTitulo());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	public static List<Integer> solicitacoesPorUsuario(Usuario usuario){
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		LinkedList<Integer> solicitacoes = new LinkedList<Integer>();
		try {
			stmt = connection.prepareStatement("select * from solicitacoes where idSolicitado =" + usuario.getIdUsuario());
				 //+" and titulo ='" + livro.getTitulo() + "'");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				solicitacoes.add(id);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}

		return solicitacoes;
	}
	
}
