package br.edu.coo2015.ep2.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.dao.UsuarioDaoHibernate;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.AutenticacaoException;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

@Resource // Indica o VRaptor que essa classe deve ser controlada por ele
public class LoginController {
	
	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;

	//private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;

	private final UsuarioDaoHibernate usuarioDaoHibernate;

	private final Validator validator;

	public LoginController(Validator validator,
			UsuarioDaoHibernate usuarioDaoHibernate, Result result,
			UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes,
			BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade) {
		this.validator = validator;
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		//this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
		this.usuarioDaoHibernate = usuarioDaoHibernate;
		//bibliotecaCompartilhadaFacade.setLivroDao(this.livroDaoHibernate);
	}

	public void login() {
	}	

	public void autentica(Usuario usuario) {
		try {
			usuario = gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
			usuarioSession.login(usuario);
			result.redirectTo(this).logadoComSucesso();
		} catch (AutenticacaoException e) {
			usuarioSession.logout();
			result.redirectTo("index?error");
		}
	}
	
	public void logadoComSucesso() {
		result.include("nome", usuarioSession.getUsuarioLogado().getNome());
	}

	public void problemaNaAutenticacao(String mensagem) {
		result.include("mensagem", mensagem);
	}

	public void listaLivros() {
		//result.include("livros",bibliotecaCompartilhadaFacade.listaTodosLivros());
	}
	
	public void mostraMensagem(String msg) {
		result.include("msg", msg);
	}
}
