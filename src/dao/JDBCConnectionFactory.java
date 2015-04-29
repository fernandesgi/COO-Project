package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionFactory {

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Cria conexao com o MySQL
			return DriverManager.getConnection("jdbc:mysql://localhost/COO","root","8516761");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
