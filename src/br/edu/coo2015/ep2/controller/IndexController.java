package br.edu.coo2015.ep2.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.UsuarioDao;
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
	
	public IndexController(Result result, UsuarioSession usuarioSession,
			GerenciadorDeAutenticacoes gerenciadorDeAutenticacoes,
			BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade) {
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.gerenciadorDeAutenticacoes = gerenciadorDeAutenticacoes;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
	}
	
	public void index(){
		
	}
	
	public void autentica(Usuario usuario) {
		System.out.println("Nome: " + usuario.getNome() +
						   "Sobrenome: " + usuario.getSobrenome() +
						   "Usuario: " + usuario.getLogin() +
						   "Senha: " + usuario.getPassword());
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
	
	public void registra(Usuario usuario){
		System.out.println("Nome: " + usuario.getNome() +
				   "Sobrenome: " + usuario.getSobrenome() +
				   "Usuario: " + usuario.getLogin() +
				   "Senha: " + usuario.getPassword());
		
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.adiciona(usuario);
	}
	
	public void logadoComSucesso() {
		result.include("nome", usuarioSession.getUsuarioLogado().getNome());
		result.redirectTo(new HomeController()).home();
	}

	public void problemaNaAutenticacao(String mensagem) {
		result.include("mensagem", mensagem);
	}
}
