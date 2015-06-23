package br.edu.coo2015.ep2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.entity.Usuario;

public class EmprestimoDao {
	
	// Adiciona solicitacao no banco de dados
	public void adiciona(Livro livro, Integer i, Usuario usuario) {
		Connection connection = JDBCConnectionFactory.getConnection();
		if (usuario.getIdUsuario() == i) {
			throw new IllegalArgumentException("Você ja possui esse livro");
		}

		if (estaEmprestado(livro, i) == true){
			throw new IllegalArgumentException("O livro ja esta emprestado");
		}
		try {
			/*
			// Faz a solicitacao apenas pra quem nao tinha enviado ainda
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
			*/
			
			// Faz a solicitacao pela primeira vez
			String sql = "insert into solicitacoes (idSolicitante,idSolicitado,titulo,autor,status) values (?,?,?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, usuario.getIdUsuario());
			stmt.setInt(2, i);
			stmt.setString(3, livro.getTitulo());
			stmt.setString(4, livro.getAutor());
			stmt.setString(5, "PENDENTE");
			stmt.execute(); // executar o comando
			stmt.close(); // fechar o comando
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}
	}
	
	// Verifica se o livro esta emprestado
	private boolean estaEmprestado(Livro livro, Integer i){
		Boolean emprestado = false;
		Connection connection = JDBCConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("select * from livros where titulo =? and idUsuario=?");
			stmt.setString(1, livro.getTitulo());
			stmt.setInt(2,i);
			ResultSet rs = stmt.executeQuery();
			emprestado = rs.getBoolean("emprestado");
			rs.close();
			stmt.close();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
		
		return emprestado;
	}
	
	//Remove solicitacao no Banco de Dados
	public void remove(Usuario usuario, Livro livro){
		Connection connection = JDBCConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("delete from solicitacoes where idSolicitante=? and titulo =?");
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
	
	
	public List<Integer> solicitacoesPorUsuario(Usuario usuario){
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		LinkedList<Integer> solicitacoes = new LinkedList<Integer>();
		try {
			stmt = connection.prepareStatement("select * from solicitacoes where idSolicitado =" + usuario.getIdUsuario());
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
