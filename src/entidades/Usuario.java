package entidades;

import dao.UsuarioDao;

public class Usuario {
	
	private int idUsuario; // eh unico de cada usuario
	private String login; // tem que ser email
	private String password; // TODO fazer o email ser login e o login
	private String nome;
	private String sobrenome;
	private double credibilidade; 
	//private Calendar dataNascimento;
	
	// Construtor
	public Usuario(String login, String password,String nome,String sobrenome){
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.idUsuario = new UsuarioDao().getId(this);
		this.credibilidade = 5.00; // Padrao: NEUTRO
	}
	
	public int getIdUsuario(){
		return this.idUsuario;
	}

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public double getCredibilidade() {
		return credibilidade;
	}

	// solicita o emprestimo
	public void solicitaLivro(Livro livro){
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.solicita(livro, this);
	}
	
	@Override
	public String toString() {
		return "Login: " + this.login + " Password: " + this.password
				+ " Nome: " + this.nome + " Sobrenome: " + this.sobrenome;
	}

	//TODO metodo adicionaLivro

}
