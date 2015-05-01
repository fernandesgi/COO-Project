package entidades;

import dao.LivroDao;
import dao.UsuarioDao;

public class Usuario {
	//TODO Apagar os getters e setters desnecesarios 
	//TODO IMPRIMIR O LOG DO BANCO DE DADOS
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
	
	// Adiciona Livro no Banco de Dados
	private void adicionaLivro(String titulo, String autor, String editora){
		Livro livro = new Livro(titulo,autor,editora,getIdUsuario());
		LivroDao ld = new LivroDao();
		ld.adiciona(livro);
	}
	
	// Remove Livro no Banco de Dados
	private void removeLivro(String titulo, String autor, String editora){
		Livro livro = new Livro(titulo,autor,editora,getIdUsuario());
		LivroDao ld = new LivroDao();
		ld.remove(livro);
	}
	// solicita o emprestimo
	private void solicitaLivro(Livro livro){
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.solicita(livro, this);
	}
	
	// Cria um usuario
	private void adicionaUsuario(){
		UsuarioDao ud = new UsuarioDao();
		if (verificaCriacaoLogin(this.login) == true) ud.adiciona(this);
		else throw new IllegalArgumentException("Login deve ser um email");
	}
	
	// Verifica se login == email
	private boolean verificaCriacaoLogin(String login){
		char[] c = login.toCharArray();
        for (int i = 0; i < c.length; i++){
        	if(c[i] == '@') return true;
        }
		return false;
	}
	
	@Override
	public String toString() {
		return "Login: " + this.login + " Password: " + this.password
				+ " Nome: " + this.nome + " Sobrenome: " + this.sobrenome;
	}


}
