package entidades;

import java.io.Serializable;

public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String autor; 
	private String editora;
	private int idUsuario;  // O livro so ira ser criado quando um usuario adicionar ele
	
	// Construtores
	public Livro(String titulo, String autor, String editora, Usuario usuario) {
		setTitulo(titulo);
		setAutor(autor);
		setEditora(editora);
		setIdUsuario(usuario.getIdUsuario());
	}
	
	public Livro(String titulo, String autor, String editora, int usuarioID) {
		setTitulo(titulo);
		setAutor(autor);
		setEditora(editora);
		setIdUsuario(usuarioID);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	// sobrescrevemos o método "toString" para imprimir o objeto Livro no
	// console
	@Override
	public String toString() {
		return "Titulo: " + this.titulo + " Autor: " + this.autor + " Editora: " + this.editora;
	}
}
