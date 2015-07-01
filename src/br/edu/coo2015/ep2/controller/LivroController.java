package br.edu.coo2015.ep2.controller;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.entity.Emprestimo;
import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.model.BibliotecaCompartilhadaFacade;
import br.edu.coo2015.ep2.security.RestritoUsuarioCadastrado;

@Resource
public class LivroController {
	
	private final Result result;
	private final LivroDaoHibernate livroDaoHibernate;
	private final UsuarioSession usuarioSession;
	private final BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade;
	
	public LivroController(UsuarioSession usuarioSession, LivroDaoHibernate livroDao, Result result, BibliotecaCompartilhadaFacade bibliotecaCompartilhadaFacade){
		this.result = result;
		this.usuarioSession = usuarioSession;
		this.livroDaoHibernate = livroDao;
		this.bibliotecaCompartilhadaFacade = bibliotecaCompartilhadaFacade;
		this.bibliotecaCompartilhadaFacade.setLivroDao(this.livroDaoHibernate);
	}
	
	@RestritoUsuarioCadastrado
	public void meusLivros(){
		List<Livro> list = (List<Livro>) livroDaoHibernate.busca(usuarioSession.getUsuarioLogado().getId());
		result.include("livros", list);
	}
	
	@RestritoUsuarioCadastrado
	public void adicionaLivro() {
	}
	
	@RestritoUsuarioCadastrado
	public void submeteLivro(Livro livro) {
		livro.setIdUsuario(usuarioSession.getUsuarioLogado().getId());
		livroDaoHibernate.adiciona(livro);
		result.redirectTo("adicionaLivro?success");
	}
}
