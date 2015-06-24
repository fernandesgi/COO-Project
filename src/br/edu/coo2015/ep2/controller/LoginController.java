package br.edu.coo2015.ep2.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.AutenticacaoException;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

/**
 * 
 * Este eh um exemplo de como utilizar o VRaptor no EP2
 * 
 * Framework VRaptor para controlador web: http://www.vraptor.org/
 * A versao mais recente do vraptor eh a 4, mas estamos utilizando o VRaptor 3
 *  
 * Material para estudo: http://www.caelum.com.br/apostila-vraptor-hibernate/  
 *  
 */

@Resource // Indica o VRaptor que essa classe deve ser controlada por ele
public class LoginController {

	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;

	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;

	public LoginController(Result result, UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes,
			BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade) {
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
	}

	public void login() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		Date date = new Date();
		result.include("dataHoje", dateFormat.format(date));
	}	

	public void autentica(Usuario usuario) {
		try {
			gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
			usuarioSession.login(usuario);
			result.redirectTo(this).logadoComSucesso();
		} catch (AutenticacaoException e) {
			// No redirectTo, o request eh perdido (eh iniciado um novo)
			// No forwardTo o request eh passado para frente
			usuarioSession.logout();
			result.redirectTo(this).problemaNaAutenticacao(e.getMessage());
		}
	}

	public void logadoComSucesso() {
		result.include("nome", usuarioSession.getUsuarioLogado().getNome());
	}

	public void problemaNaAutenticacao(String mensagem) {
		result.include("mensagem", mensagem);
	}

	public void listaLivros() {
		result.include("livros",
				bibliotecaCompartilhadaFacade.listaTodosLivros());
	}
	
	public void mostraMensagem(String msg) {
		result.include("msg", msg);
	}
}
