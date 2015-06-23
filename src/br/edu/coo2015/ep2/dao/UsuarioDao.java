package br.edu.coo2015.ep2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.coo2015.ep2.entity.Usuario;

public class UsuarioDao {
	
	public UsuarioDao(){
	}
	
	public void adiciona(Usuario usuario){
		if (usuario == null) {
			throw new IllegalArgumentException("Parametro usuario nao pode ser nulo.");
		}
		
		if (buscaPorLogin(usuario.getLogin()) != null){
			throw new IllegalArgumentException("Já existe um usuario com esse login");
		}

		if (usuario.getLogin() == null) {
			throw new IllegalArgumentException("Nao eh possivel adicionar um usuario sem login.");
		}
		
		if (usuario.getPassword() == null) {
			throw new IllegalArgumentException("Nao eh possivel adicionar um usuario sem senha");
		}
		
		if (usuario.getNome() == null){
			throw new IllegalArgumentException("Nao eh possivel adicionar um usuario sem nome");
		}

		if (usuario.getSobrenome() == null){
			throw new IllegalArgumentException("Nao eh possivel adicionar um usuario sem sobrenome");
		}
		
		Connection connection = JDBCConnectionFactory.getConnection();
		String sql = "insert into usuarios (login, password, nome, sobrenome, credibilidade) values (?,?,?,?,?)";
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			//Date data = new Date(usuario.getDataNascimento().getTime().getTime());
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, usuario.getSobrenome());
			stmt.setDouble(5, usuario.getCredibilidade());
			stmt.execute(); // executar o comando
			stmt.close(); // fechar o comando
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}
	}
	
	// Retorna usuario com aquele login
	public Usuario buscaPorLogin(String login) {
		if (login == null) {
			throw new IllegalArgumentException("Parametro login nao pode ser nulo.");
		}

		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		Usuario usuario = null;
		try {
			stmt = connection.prepareStatement("select * from usuarios where login=?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery(); // retorna o ResultSet com o excecuteQuery

			if (rs.next()) { // assume que nao tem repeticao
				String log = rs.getString("login");
				String nome = rs.getString("nome");
				String sobrenome = rs.getString("sobrenome");
				String senha = rs.getString("password");
				usuario = new Usuario(log, senha, nome, sobrenome);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection);
		}

		return usuario;
	}

	// Remove usuario
	public void remove(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Parametro usuario nao pode ser nulo.");
		}

		if (usuario.getLogin() == null) {
			throw new IllegalArgumentException("Nao eh possivel remover um usuario sem login.");
		}
		
		if (buscaPorLogin(usuario.getLogin()) == null) { 
			throw new IllegalArgumentException("Nao existe usuario com o login\"" + usuario.getLogin() + "\" na base de dados.");
		}
		
		Connection connection = JDBCConnectionFactory.getConnection();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("delete from usuarios where login=?");
			stmt.setString(1, usuario.getLogin());
			stmt.execute(); // executar o comando
			stmt.close(); // fechar o comando
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}

	}
	
	// Retorna o id do Usuario salvo no bando de dados
	public int getId(Usuario usuario){
		int idUser = -1;
		Connection connection = JDBCConnectionFactory.getConnection();
		try{
			String sql = "select id from usuarios where login = '" + usuario.getLogin() + "'" ;
			PreparedStatement stmtID = connection.prepareStatement(sql);
			ResultSet id = stmtID.executeQuery();
			
			// Intera no ResultSet
			while(id.next()){
				idUser = id.getInt("id");
			}
			stmtID.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JDBCConnectionFactory.close(connection); // fecha a conexao com o BD
		}
		
		return idUser;
	}
	
}
