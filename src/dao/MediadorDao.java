package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Livro;
import entidades.Usuario;

public class MediadorDao {
	
	//Muda o status da solicitacao para aceita
	public void emprestimoAceitado(Integer id){
		if (id == null){
			throw new IllegalArgumentException("solicitacao nao existente");
		}
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			String sql = "update solicitacoes set status=? where id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "SOLICITACAO ACEITA");
			stmt.setInt(2, id);
			stmt.execute();
	        stmt.close();
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	//Apaga a solicitacao do Banco de dados
	public void emprestimoRecusado(Integer id){
		if (id == null){
			throw new IllegalArgumentException("solicitacao nao existente");
		}
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			PreparedStatement stmt = connection.prepareStatement("delete from solicitacoes where id=" + id);
			stmt.execute();
			stmt.close();	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	//Livro esta com o mediador
	public void emprestimoMediado(Integer id){
		if (id == null){
			throw new IllegalArgumentException("solicitacao nao existente");
		}
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			String sql = "update solicitacoes set status=? where id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "ENTREGUE AO MEDIADOR");
			stmt.setInt(2, id);
			stmt.execute();
	        stmt.close();
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}		
	}
	
	//Quando o usuario solicitante recebe o livro
	public void emprestimoExecutado(Integer id){
		if (id == null){
			throw new IllegalArgumentException("solicitacao nao existente");
		}
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			String sql = "update solicitacoes set status=? where id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "ENTREGUE AO USUARIO SOLICITANTE");
			stmt.setInt(2, id);
			stmt.execute();
	        stmt.close();
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	//Quando o livro foi devolvido
	public void emprestimoConcluido(Integer id){
		if (id == null){
			throw new IllegalArgumentException("solicitacao nao existente");
		}
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			String sql = "update solicitacoes set status=? where id=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "CONCLUIDO");
			stmt.setInt(2, id);
			stmt.execute();
	        stmt.close();
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
}
