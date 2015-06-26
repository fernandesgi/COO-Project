package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.LivroDao;
import br.edu.coo2015.ep2.dao.UsuarioDao;
import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.entity.Usuario;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.model.GerenciadorDeAutenticacoes;

@Resource
public class HomeController {
	
	public HomeController(){
	
	}
	
	public void home(){
		
	}
	
	public void adicionaLivro(Livro livro){
		System.out.println("Titulo: " + livro.getTitulo());
		LivroDao livroDao = new LivroDao();
		livroDao.adiciona(livro);
	}
	
	public void configuracoes(){
		
	}
	
	public void meusLivros(){
		
	}
	
	public void buscaLivro(){
		
	}
}
