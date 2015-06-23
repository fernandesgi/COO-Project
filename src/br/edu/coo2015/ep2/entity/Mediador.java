package br.edu.coo2015.ep2.entity;

import br.edu.coo2015.ep2.dao.MediadorDao;


public class Mediador {
	/* Avisa os usuarios quando recebe os livros, entrega e os devolve */
	
	// Usuario avisa ao mediador que entreqou o livro pra ele
	public void entregueMediador(Integer idSolicitacao){
		recebido(idSolicitacao);
	}
	
	// Usuario avisa que recebeu o livro a ser emprestado do mediador
	public void recebiMediadorEmprestimo (Integer idSolicitacao){
		entregueSolicitante(idSolicitacao);
	}
	
	// Usuario avisa que recebeu de volta o livro do mediador
	public void recebiMediadorDevolucao(Integer idSolicitacao){
		devolucao(idSolicitacao);
	}
	
	// Alega que recebeu o livro
	private void recebido(Integer idSolicitacao){
		boolean recebi = verifica(idSolicitacao);
		if(recebi == true){
			MediadorDao md = new MediadorDao();          
			md.emprestimoMediado(idSolicitacao);		 // Atualiza o status para Entregue ao Solicitante
		}
		else throw new IllegalArgumentException("Livro nao recebido");
	}
	
	// Alega que entregou ao solicitante
	private void entregueSolicitante(Integer idSolicitacao){
		boolean recebi = verifica(idSolicitacao);
		if(recebi == true){
			MediadorDao md = new MediadorDao();           
			md.emprestimoExecutado(idSolicitacao);       // Atualiza o status para Entregue ao Solicitante
		}
		else throw new IllegalArgumentException("Livro nao recebido");
	}
	
	// Alega que devolveu o livro pro dono
	private void devolucao(Integer idSolicitacao){
		boolean recebi = verifica(idSolicitacao);
		if(recebi == true){
			MediadorDao md = new MediadorDao();          // Atualiza o status do Concluido
			md.emprestimoConcluido(idSolicitacao);       // Deixa armazenado no sistema
		}
		else throw new IllegalArgumentException("Livro nao recebido");
	}
	
	private boolean verifica(Integer idSolicitacao){
		//TODO mostra uma tela que diz se o mediador recebeu ou nao o livro
		//Nao implementado para o EP1
		return true;
	}
}
