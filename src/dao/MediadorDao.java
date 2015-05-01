package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Livro;

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
	        
	        // altera o status do livro para "nao esta mais emprestado (emprestado = 0)"
	        Livro livro = achaLivro(id);
			naoEstaEmprestado(livro);
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	// Altera no banco de dados que o livro esta empretado [emprestado = 1 = true]
	public void estaEmprestado(Livro livro){
		Connection connection = JDBCConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("update livros set emprestado=? where titulo=? and idUsuario=?");
			stmt.setBoolean(1, true);
			stmt.setString(2, livro.getTitulo());
			stmt.setInt(3, livro.getIdUsuario());
			stmt.execute();
			stmt.close();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
		
	}
	
	public void naoEstaEmprestado(Livro livro){
		Connection connection = JDBCConnectionFactory.getConnection();
		try {
			PreparedStatement stmt = connection.prepareStatement("update livros set emprestado=? where titulo=? and idUsuario=?");
			stmt.setBoolean(1, false);
			stmt.setString(2, livro.getTitulo());
			stmt.setInt(3, livro.getIdUsuario());
			stmt.execute();
			stmt.close();
				
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
	
	// Acha o livro pelo id da solicitacao do emprestimo
	public Livro achaLivro(Integer idSolicitacao){
		Livro livro = null;
		Connection connection = JDBCConnectionFactory.getConnection();
		try {
			// Acha o id do Usuario que possui o livro
			PreparedStatement stmt = connection.prepareStatement("select * from solicitacoes where id=?");
			stmt.setInt(1, idSolicitacao);
			ResultSet rs = stmt.executeQuery();
			Integer idUsuario = null;
			String titulo = null;
			if (rs.next()) { 
				idUsuario = rs.getInt("idSolicitado");
				titulo = rs.getString("titulo");
			}
			rs.close();
			stmt.close();
			
			if (idUsuario == null & titulo == null ){
				throw new IllegalArgumentException("Nao achei o usuario do livro");
			}
			
			// Acha o livro e retorna ele
			PreparedStatement s = connection.prepareStatement("select * from livros where idUsuario=? and titulo=?");
			s.setInt(1, idUsuario);
			s.setString(2, titulo);
			ResultSet r = s.executeQuery();
			if (r.next()) { 
				String t = r.getString("titulo");
				String a = r.getString("autor");
				String e = r.getString("editora");
				livro = new Livro(t,a,e,idUsuario);	
			}
			s.close();
			r.close();
			return livro;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}
	}
}
