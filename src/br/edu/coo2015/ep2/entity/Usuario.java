package br.edu.coo2015.ep2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Length(min = 5)
	private String login; // tem que ser email
	
	@Length(min = 6)
	private String password;
	
	@Length(min = 1)
	private String nome;
	@Length(min = 1)
	private String sobrenome;
	
	private double credibilidade;

	
	public long getId() {
		return id;
	}

	public void setId(long idUsuario) {
		this.id = idUsuario;
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

//	// Verifica se login == email
//	private static boolean verificaCriacaoLogin(String login){
//		char[] c = login.toCharArray();
//	    for (int i = 0; i < c.length; i++){
//	  	if(c[i] == '@'){
//	   		while(i < c.length){
//	   			if(c[i] == '.') return true;
//	   			i++;
//	       		}
//	       	}
//	    }
//		return false;
//	}	
//	
//	// Adiciona Livro no Banco de Dados
//	private void adicionaLivro(String titulo, String autor, String editora){
//		Livro livro = new Livro(titulo,autor,editora,getIdUsuario());
//		LivroDao ld = new LivroDao();
//		ld.adiciona(livro);
//	}
//	
//	// Remove Livro no Banco de Dados
//	private void removeLivro(String titulo, String autor, String editora){
//		Livro livro = new Livro(titulo,autor,editora,getIdUsuario());
//		LivroDao ld = new LivroDao();
//		ld.remove(livro);
//	}
//	// solicita o emprestimo
//	private void solicitaLivro(Livro livro){
//		Emprestimo emprestimo = new Emprestimo();
//		emprestimo.solicita(livro, this);
//	}
//	
//	// Cria um usuario
//	private void adicionaUsuario(){
//		UsuarioDao ud = new UsuarioDao();
//		if (verificaCriacaoLogin(this.login) == true) ud.adiciona(this);
//		else throw new IllegalArgumentException("Login deve ser um email valido");
//	}
	
	
	
	@Override
	public String toString() {
		return 		" Login: " + this.login
				+ 	" Nome: " + this.nome
				+ 	" Sobrenome: " + this.sobrenome;
	}


}
