package entidades;

import java.util.List;

import dao.LivroDao;
import dao.MediadorDao;
import dao.UsuarioDao;

public class Usuario {
	
	private int idUsuario; // eh unico de cada usuario
	private String login; // tem que ser email
	private String password; // TODO fazer o email ser login e o login
	private String nome;
	private String sobrenome;
	private double credibilidade; //TODO implementar de 0 a 10
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

	// Será chamado quando estiver na tela do Livro ja encontrado no sistema
	public void solicita(Livro livro){
		//LivroDao ld = new LivroDao();
		//ld.listaTodosPorUsuario(livro);
		enviaSolicitacao(livro); //faz a solicitacao pra todos que possuem o livro
		//TODO avisa todos que possuem os livro da solicitação
		//TODO se alguem aceitar, avisar o solicitador
	}
	
	private void enviaSolicitacao(Livro livro){
		LivroDao ld = new LivroDao();
		MediadorDao md = new MediadorDao();
		List<Integer> lista= ld.listaTodosPorUsuario(livro);
		for (Integer i : lista) {
			// Salvo no BD a solicitacao
			md.adiciona(livro, i, this);
		}
	}
		
	//retorna true se quiser emprestar e false se n quiser
	public boolean emprestar(Livro livro){
		return false;
	}

	private void mostarSolicitacoes(Usuario usuario){
		MediadorDao md = new MediadorDao();
		List<Integer> lista = md.solicitacoesPorUsuario(usuario);
		for (Integer i : lista) {
			System.out.print(i + " "); //TODO criar metodo de busca por id que retorna titulo
		}
		//fazer metodo para aceitar o titulo escolhido
		
	}		
	
	@Override
	public String toString() {
		return "Login: " + this.login + " Password: " + this.password
				+ " Nome: " + this.nome + " Sobrenome: " + this.sobrenome;
	}

	//TODO metodo adicionaLivro

}
