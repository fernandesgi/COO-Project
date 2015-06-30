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
import br.edu.coo2015.ep2.security.RestritoUsuarioCadastrado;

@Resource // Indica o VRaptor que essa classe deve ser controlada por ele
public class LoginController {
	
	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;

	private final UsuarioDaoHibernate usuarioDaoHibernate;

	private final Validator validator;

	public LoginController(Validator validator,
			UsuarioDaoHibernate usuarioDaoHibernate, Result result,
			UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes) {
		this.validator = validator;
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.usuarioDaoHibernate = usuarioDaoHibernate;
	}
	
	@RestritoUsuarioCadastrado
	public void login() {
	}	
	
	@RestritoUsuarioCadastrado
	public void autentica(Usuario usuario) {
		try {
			usuario = gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
			usuarioSession.login(usuario);
			result.redirectTo(HomeController.class).home();
		} catch (AutenticacaoException e) {
			usuarioSession.logout();
			result.redirectTo("login?error");
		}
	}
	
	public void registra(Usuario usuario){
		try {
			usuarioDaoHibernate.adiciona(usuario);
			result.redirectTo("index?success");
		} catch (Exception e) {
			// TODO
			result.redirectTo("index?erroEmail");
		}
	}
}
