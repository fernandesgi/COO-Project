package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.UsuarioDaoHibernate;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.AutenticacaoException;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

@Resource
public class ConfiguracoesController {

	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;
	
	private final UsuarioDaoHibernate usuarioDaoHibernate;
	
	public ConfiguracoesController(Result result, UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes, UsuarioDaoHibernate usuarioDaoHibernate) {
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.usuarioDaoHibernate = usuarioDaoHibernate;
	}
	
	public void configuracoes() {
	}
	
	public void alteraConta(){
	}
	
	public void mudaSenha(String senha, String nova) {
		Usuario logado = usuarioSession.getUsuarioLogado();
		if(senha.equals(logado.getPassword())) {
			logado.setPassword(nova);
			usuarioDaoHibernate.update(logado);
			result.redirectTo("configuracoes?success");
		}
		else {
			result.redirectTo("alteraConta?error");
		}
	}
	
	public void deletaConta(){
		
	}
	
	public void deletaContaSucesso(Usuario usuario){
		System.out.println("Usuario: " + usuario.getLogin() + "Senha: " + usuario.getPassword());
		
		try{
			gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
		} catch (AutenticacaoException e) {
			result.redirectTo(IndexController.class).index();
		}
	}
}
