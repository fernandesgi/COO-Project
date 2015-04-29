package testes;

import dao.LivroDao;
import entidades.Livro;

public class TestaLivroDao {

	static final String[] titulos = { "Capit�es da Areia", "O Tempo e o Vento", "Vidas Secas", "Senhora", "A Moreninha",
			"Os Lus�adas", "Iracema", "Dom Casmurro", "Macuna�ma", "O Corti�o", "A Hora da Estrela",
			"Mem�rias de um Sargento de Mil�cias", "Os Sert�es", "Auto da Barca do Inferno" };
	static final String[] autores = { "Jorge Amado", "�rico Ver�ssimo", "Graciliano Ramos", "Jos� de Alencar",
			"Joaquim Manuel Macedo", "Lu�s de Cam�es", "Jos� de Alencar", "Machado de Assis", "M�rio de Andrade",
			"Alu�sio Azevedo", "Clarice Lispector", "Manuel Ant�nio de Almeida", "Euclides da Cunha", "Gil Vicente" };

	static final String[] editoras = { "Editora Abril", "Editora ABC", "Editora Alpha", "Editora Beta", "Editora Abril",
			"Editora ABC", "Editora Alpha", "Editora Beta", "Editora Abril", "Editora ABC", "Editora Alpha", "Editora Beta",
			"Editora Abril", "Editora ABC" };

	public static void main(String[] args) {
		limpaBD();
		//teste1();
		//teste2();
	}

	static void teste1() {
		populaBD();
		testaBuscaPorTitulo();
		testaListagens();
		testaRemocao();
		testaListagens();
		limpaBD();
	}
	
	static void teste2() {
		populaBD();
		testaAtualiza();
		testaListagens();
		limpaBD();
	}
	
	static void populaBD() {
		LivroDao livroDao = new LivroDao();

		for (int i = 0; i < autores.length; i++) {
			livroDao.adiciona(new Livro(autores[i], titulos[i], editoras[i],-1));
		}
	}

	static void limpaBD() {
		LivroDao livroDao = new LivroDao();

		for (Livro livro : livroDao.listaTodos()) {
			livroDao.remove(livro);
		}
	}

	static void testaBuscaPorTitulo() {
		LivroDao livroDao = new LivroDao();
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("------------------------ Testa busca por t�tulo -----------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");

		for (int i = 0; i < titulos.length; i++) {
			System.out.println(livroDao.buscaPorTitulo(titulos[i])); //imprime o toString
		}

		System.out.println("\n");
	}

	static void testaListagens() {
		LivroDao livroDao = new LivroDao();

		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("------------------------- Livros ordenados por autor ------------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		for (Livro livro : livroDao.listaTodosOrdenandoPorAutor()) {
			System.out.println(livro);
		}
		System.out.println("\n");

		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("------------------------- Livros ordenados por t�tulo -----------------------------");
		System.out.println("-----------------------------------------------------------------------------------");
		for (Livro livro : livroDao.listaTodosOrdenandoPorTitulo()) {
			System.out.println(livro);
		}
		System.out.println("\n");
	}

	static void testaRemocao() {
		LivroDao livroDao = new LivroDao();

		for (int i = 0; i < titulos.length / 2; i++) {
			livroDao.remove(livroDao.buscaPorTitulo(titulos[i]));
		}
	}

	static void testaAtualiza() {
		LivroDao livroDao = new LivroDao();

		for (int i = 0; i < titulos.length / 2; i++) {
			Livro livro = livroDao.buscaPorTitulo(titulos[i]);
			livro.setAutor("Autor " + i);
			livro.setEditora("Editora " + i);
			livroDao.atualiza(livro);
		}
	}
}
