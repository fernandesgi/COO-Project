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

	public IndexController(Result result) {
		this.result = result;
	}
	
	public void index() {
		result.redirectTo(LoginController.class).login();
	}
	
}
