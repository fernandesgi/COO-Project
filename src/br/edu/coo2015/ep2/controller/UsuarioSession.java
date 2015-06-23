package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.edu.coo2015.ep2.dao.UsuarioDao;
import br.edu.coo2015.ep2.entity.Usuario;

@Component
@SessionScoped
public class UsuarioSession {
	
	private Usuario usuario;
	
	public boolean estaLogado(){
		return usuario != null;
	}
	
	public void login(Usuario usuario) {
		usuario = new UsuarioDao().buscaPorLogin(usuario.getLogin());
		this.usuario = usuario;
	}
	
	public void logout() {
		this.usuario = null;
	}
	
	public Usuario getUsuarioLogado() {
		return usuario;
	}
}
