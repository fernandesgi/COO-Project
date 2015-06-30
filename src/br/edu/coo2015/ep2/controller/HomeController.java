package br.edu.coo2015.ep2.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;

@Resource
public class HomeController {
	
	private final LivroDaoHibernate livroDaoHibernate;
	
	private final Result result;
	
	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;
	
	public HomeController(LivroDaoHibernate livroDaoHibernate, Result result, BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade){
		this.livroDaoHibernate = livroDaoHibernate;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
		this.result = result;
		this.bibliotecaCompartilhadaFacade.setLivroDao(livroDaoHibernate);
	}
	
	public void home(){
	}
	
	public void adicionaLivro() {
	}

	public void submeteLivro(Livro livro) {
		livroDaoHibernate.adiciona(livro);
		result.redirectTo("adicionaLivro?success");
	}
	
	public void meusLivros(){
		List<Livro> list = (List<Livro>) bibliotecaCompartilhadaFacade.listaTodosLivros();
		result.include("livros", list);
	}
	
	public void buscaLivro(){
	}
	
	public void verTodos() {
		List<Livro> list = (List<Livro>) bibliotecaCompartilhadaFacade.listaTodosLivros();
		result.include("livros", list);
	}
}
