package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.UsuarioDaoHibernate;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.AutenticacaoException;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

@Resource
public class IndexController {
	
	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;

	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;
	
	private final UsuarioDaoHibernate usuarioDaoHibernate;
	
	public IndexController(Result result, UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes,
			BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade, UsuarioDaoHibernate usuarioDaoHibernate) {
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
		this.usuarioDaoHibernate = usuarioDaoHibernate;
	}
	
	public void index() {
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
	
	public void registra(Usuario usuario){
		try {
			usuarioDaoHibernate.adiciona(usuario);
			result.redirectTo("index?success");
		} catch (Exception e) {
			//TODO
			result.redirectTo("index?erroEmail");
		}
	}
	
	public void logadoComSucesso() {
		result.redirectTo(HomeController.class).home();
	}

}
