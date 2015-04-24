public class ContatoDao{
	// a conexão com o banco de dados
	private Connection connection;

	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	  
	public void adiciona(Contato contato) {
		String sql = "insert into contacts " + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";

		try {
			// prepared statement para inserção
		    PreparedStatement stmt = con.prepareStatement(sql);

		    // seta os valores

		    stmt.setString(1,contato.getNome());
		    stmt.setString(2,contato.getEmail());
		    stmt.setString(3,contato.getEndereco());
		    stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

		    // executa
		    stmt.execute();
		    stmt.close();
		    } catch (SQLException e) {
		    	throw new RuntimeException(e);
		    }
		}
}