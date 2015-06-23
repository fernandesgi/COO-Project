package br.edu.coo2015.ep2.model;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.dao.UsuarioDao;
import br.edu.coo2015.ep2.entity.Usuario;

@Component
public class GerenciadorDeAutenticacoes {
	private final UsuarioDao usuarioDao;
	
	public GerenciadorDeAutenticacoes(UsuarioDao usuarioDao){
		this.usuarioDao = usuarioDao;
	}

	public boolean autenticaUsuarioComum(Usuario usuario) throws AutenticacaoException {
		Usuario usuarioBD = usuarioDao.buscaPorLogin(usuario.getLogin());
		if(usuarioBD == null || !usuarioBD.getPassword().equals(usuario.getPassword()))
			throw new AutenticacaoException("Usuario e/ou senha inexistente.");
		return true;
	}
}
