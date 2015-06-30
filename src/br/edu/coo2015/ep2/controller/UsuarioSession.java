package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.edu.coo2015.ep2.dao.UsuarioDaoHibernate;
import br.edu.coo2015.ep2.entity.Usuario;

@Component
@SessionScoped
public class UsuarioSession {
	
	private Usuario usuario;
	
	public boolean estaLogado(){
		boolean logado = (usuario == null);
		return logado;
	}
	
	public void login(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void logout() {
		this.usuario = null;
	}
	
	public Usuario getUsuarioLogado() {
		return usuario;
	}
}
