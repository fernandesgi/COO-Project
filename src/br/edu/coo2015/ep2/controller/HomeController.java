package br.edu.coo2015.ep2.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.edu.coo2015.ep2.dao.EmprestimoDaoHibernate;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.entity.Emprestimo;
import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.security.RestritoUsuarioCadastrado;

@Resource
public class HomeController {
	
	private final LivroDaoHibernate livroDaoHibernate;
	
	private final EmprestimoDaoHibernate empDaoHibernate;
	
	private final Result result;
	
	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;
	
	private final UsuarioSession usuarioSession;
		
	public HomeController(LivroDaoHibernate livroDaoHibernate, UsuarioSession usuarioSession, Result result, EmprestimoDaoHibernate empDaoHibernate ,BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade){
		this.livroDaoHibernate = livroDaoHibernate;
		this.empDaoHibernate = empDaoHibernate;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
		this.result = result;
		this.bibliotecaCompartilhadaFacade.setLivroDao(this.livroDaoHibernate);
		this.usuarioSession = usuarioSession;
	}
	
	@RestritoUsuarioCadastrado
	public void home() {
	}
	
	@RestritoUsuarioCadastrado
	public void buscaLivro() {
	}
	
	@RestritoUsuarioCadastrado
	public void buscar(Livro livro) {
		Livro novo = livroDaoHibernate.busca(livro);
		List<Emprestimo> list = empDaoHibernate.buscaEmprestados();
		for(int i =0; i < list.size() ;i++) {
			if(list.get(i).getIdLivro() == novo.getId()) novo = null;
		}
		usuarioSession.setLivroASolicitar(novo);
		result.include("livro", novo);
	}
	
	@RestritoUsuarioCadastrado
	public void solicitar(){
		Emprestimo emp = new Emprestimo();
		emp.setIdLivro(usuarioSession.getLivroASolicitar().getId());
		emp.setIdEmprestador(usuarioSession.getLivroASolicitar().getIdUsuario());
		emp.setIdSolicitador(usuarioSession.getUsuarioLogado().getId());
		empDaoHibernate.adiciona(emp);
		result.redirectTo(HomeController.class).home();
	}
	
	@RestritoUsuarioCadastrado
	public void meusLivros(){
		result.redirectTo(LivroController.class).meusLivros();
	}
	
	@RestritoUsuarioCadastrado
	public void verTodos() {
		List<Livro> list = (List<Livro>) bibliotecaCompartilhadaFacade.listaTodosLivros();
		List<Emprestimo> lista = empDaoHibernate.buscaEmprestados();
		for(int i =0; i < lista.size() ;i++) {
			for(int j =0; j < list.size() ; j++) {
				if(lista.get(i).getIdLivro() == list.get(j).getId()) list.remove(j);
			}
		}
		
		result.include("livros", list);
	}
}
