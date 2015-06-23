package br.edu.coo2015.ep2.entity;

import java.util.List;

import br.edu.coo2015.ep2.dao.EmprestimoDao;
import br.edu.coo2015.ep2.dao.LivroDao;
import br.edu.coo2015.ep2.dao.MediadorDao;

public class Emprestimo {
	/* Cria/remove uma solicitacao de emprestimo e salvar ela no Banco de Dados
	 * Aceita e Recusa uma solicitacao de emprestimo
	 * Envia uma notificacao para o usuario que possui o livro solicitado */
	
	// Será chamado quando estiver na tela do Livro ja encontrado no sistema
	public void solicita(Livro livro, Usuario usuario){
		enviaSolicitacao(livro, usuario);
		//TODO se alguem aceitar, avisar o solicitador
	}
	
	// Salva no BD a solicitacao pra todos que possuem o livro
	private void enviaSolicitacao(Livro livro, Usuario usuario){
		LivroDao ld = new LivroDao();
		EmprestimoDao ed = new EmprestimoDao();
		List<Integer> lista= ld.listaTodosPorUsuario(livro);
		for (Integer i : lista) {
			ed.adiciona(livro, i, usuario); // Salvo no BD a solicitacao
			notificacao(usuario);
		}
	}
	
	//TODO Notifica o usuario de que ele possui uma solicitacao
	private void notificacao(Usuario usuario){
		//Nao implementado para o EP1
		//Pode aceitar diretamente daqui a solicitacao
	}

	// Mostra todas solicitacoes que o usuario possui
	public void mostrarSolicitacoes(Usuario usuario){
		EmprestimoDao ed = new EmprestimoDao();
		List<Integer> lista = ed.solicitacoesPorUsuario(usuario);
		for (Integer i : lista) {
			System.out.print(i + " "); //TODO criar metodo de busca por id que retorna titulo
		}
		//TODO pode aceitar ou recusar o pedido [Nao implementado para o EP1]
		// ao lado da tela vai aparecer [ACEITAR] | [RECUSAR]
		// dependendo da escolha, ira ser chamado algum metodo abaixo
	}
	
	// Aceita o emprestimo
	public void aceitarEmprestimo(Integer idSolicitacao){
		MediadorDao md = new MediadorDao();
		md.emprestimoAceitado(idSolicitacao); // salva a solicitacao como "aceita" no BD
		//TODO Envia uma mensagem para quem solicitou avisando que o livro foi aceitado
		Livro livro = md.achaLivro(idSolicitacao);
		md.estaEmprestado(livro); // altera o status do livro para emprestado
		
	}
	
	
	// Recusa o emprestimo
	public void recusarEmprestimo(Integer idSolicitacao){
		MediadorDao md = new MediadorDao();
		md.emprestimoRecusado(idSolicitacao); // recusa e apaga a solicitacao no BD
	}
}
