package testes;

import java.util.LinkedList;
import java.util.List;

import dao.LivroDao;
import entidades.Livro;

public class TestaLivroDao2{
	public static void main(String[] args){
		teste1();
	}
	
	public static void teste1(){
		//TODO REMOCAO POR TITULO E POR idUSUARIO
		LivroDao ld = new LivroDao();
		Livro l = new Livro("Hunger Games", "fil","Abril",1);
		Livro l1 = new Livro("Hunger Jogos", "fila","Abril",1);
		Livro l2 = new Livro("Jogos Vorazes", "fil","Abril",2);
		Livro l3 = new Livro("Hunger Games", "fil", "Abril", 2);
		
		try {
			//ld.adiciona(l);
			//ld.adiciona(l1);
			//ld.adiciona(l2);
			ld.adiciona(l3);
		} catch(IllegalArgumentException e) {
			System.out.println("peguei");
		} finally{
			List<Integer> lista= ld.listaTodosPorUsuario(l);
			//Integer i = null;
			for (Integer i : lista) {
				System.out.print(i + " , ");
			}
			
			//ld.remove(l);
			//ld.remove(l1);
			//ld.remove(l2);
			//ld.remove(l3);
		}
		
		
	}
}