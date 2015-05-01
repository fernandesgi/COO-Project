package testes;

import dao.LivroDao;
import dao.MediadorDao;
import entidades.Livro;
import entidades.Mediador;

public class TestaMediadorDao {
	public static void main(String [] args){
		// Para testar temos que mudar os metodos do mediador para public
		teste1();
		adicionaLivro();
	}
	public static void teste1(){
		Mediador mediador = new Mediador();
		MediadorDao md = new MediadorDao();
		
		//muda o status da solicitacao
		//mediador.entregueSolicitante(7);
		//mediador.devolucao(8);
		//mediador.recebido(9);
	}
	
	public static void adicionaLivro(){
		Livro livro = new Livro("Mediador","Med","ABC",10);
		LivroDao ld = new LivroDao();
		MediadorDao md = new MediadorDao();
		
		ld.adiciona(livro);
		md.estaEmprestado(livro);
		ld.remove(livro);
	}	
}
