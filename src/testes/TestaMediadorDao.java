package testes;

import dao.MediadorDao;
import entidades.Mediador;

public class TestaMediadorDao {
	public static void main(String [] args){
		// Para testar temos que mudar os metodos do mediador para public
		
		Mediador mediador = new Mediador();
		MediadorDao md = new MediadorDao();
		
		//mediador.entregueSolicitante(7);
		//mediador.devolucao(8);
		//mediador.recebido(9);
	}
}
