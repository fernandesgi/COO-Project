package testes;

import java.util.List;

import dao.MediadorDao;
import dao.UsuarioDao;
import entidades.Livro;
import entidades.Usuario;

public class TestaUsuario {
	public static void main(String [] args){
		
		UsuarioDao user = new UsuarioDao();
		Usuario usuario = new Usuario("filipefilardi@hotmail.com","teste123","Filipe","Filardi");
		Usuario usuario1 = new Usuario("Alan_Turing@hotmail.com","CriptoIsTheKey","Alan","Turing");
		Usuario usuario2 = new Usuario("SthepanH@gmail.com", "TheoryOfEverything","Stephan", "Hawking");
		user.adiciona(usuario);
		user.adiciona(usuario1);
		user.adiciona(usuario2);
		//user.remove(usuario);
		//user.remove(usuario1);
		//user.remove(usuario2);
		
		Livro livro = new Livro("Hunger Games", "fil","Abril",2);
		Livro livro1 = new Livro("Hunger Jogos", "fila","Abril",1);
		//usuario2.solicita(livro);
		//usuario2.solicita(livro1);
		MediadorDao md = new MediadorDao();
		//md.remove(usuario2, livro);
		List<Integer> lista =  md.solicitacoesPorUsuario(usuario);
		for (Integer i : lista) {
			System.out.print(i + " , ");
		}
	}

}
