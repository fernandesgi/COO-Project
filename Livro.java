import java.io.Serializable;


public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String autor; // deveria ser uma lista de autores
	private String titulo;
	private String editora; // deveria ser um objeto Editora contendo infos da
							// editora

	// Necessita ter um construtor vazio se voce cria um outro construtor
	public Livro() {
	}
	
	public Livro(long id, String autor, String titulo, String editora) {
		setId(id);
		setAutor(autor);
		setTitulo(titulo);
		setEditora(editora);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	// sobrescrevemos o método "toString" para imprimir o objeto Livro no console
	@Override
	public String toString() {
		return "Id: " + this.id + "\nAutor:   " + this.autor + "\n" + "Título:  " + this.titulo + "\n" + "Editora: " + this.editora + "\n";
	}
}
