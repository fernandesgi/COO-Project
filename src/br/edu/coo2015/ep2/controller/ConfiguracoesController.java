package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.UsuarioDao;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.AutenticacaoException;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

@Resource
public class ConfiguracoesController {

	private final Result result;

	private final UsuarioSession usuarioSession;

	private final GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes;

	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;
	
	public ConfiguracoesController(Result result, UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes,
			BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade){
		
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
	}
	
	public void alteraConta(Usuario usuario){
		System.out.println("Usuario: " + usuario.getLogin() + "Senha: " + usuario.getPassword());
		try{
			gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
			UsuarioDao usuarioDao = new UsuarioDao();
			//usuarioDao.remove(usuario);
			result.redirectTo(new HomeController()).home();
		} catch (AutenticacaoException e) {
			result.redirectTo(new HomeController()).configuracoes();
		}
	}
	
	public void deletaConta(){
		
	}
	
	public void deletaContaSucesso(Usuario usuario){
		System.out.println("Usuario: " + usuario.getLogin() + "Senha: " + usuario.getPassword());
		
		try{
			gerenciadorDeAutenticacoes.autenticaUsuarioComum(usuario);
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.remove(usuario);
			result.redirectTo(new IndexController(null, null, null, null)).index();
		} catch (AutenticacaoException e) {
			result.redirectTo(new HomeController()).configuracoes();
		}
	}
}
