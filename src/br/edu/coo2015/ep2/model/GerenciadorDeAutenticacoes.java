package br.edu.coo2015.ep2.model;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.dao.UsuarioDaoHibernate;
import br.edu.coo2015.ep2.entity.Usuario;

@Component
public class GerenciadorDeAutenticacoes {
	
	private final UsuarioDaoHibernate usuarioDaoHibernate;
	
	public GerenciadorDeAutenticacoes(UsuarioDaoHibernate usuarioDao){
		this.usuarioDaoHibernate= usuarioDao;
	}

	public Usuario autenticaUsuarioComum(Usuario usuario) throws AutenticacaoException {
		Usuario usuarioBD = usuarioDaoHibernate.busca(usuario);
		if(usuarioBD == null || !usuarioBD.getPassword().equals(usuario.getPassword()))
			throw new AutenticacaoException("Usuario e/ou senha inexistente.");
		return usuarioBD;
	}
}
