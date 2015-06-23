package br.edu.coo2015.ep2.model;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.dao.LivroDao;
import br.edu.coo2015.ep2.entity.Livro;

@Component
public class BibliotecaCompartilhadaFacade {

	public List<Livro> listaTodosLivros() {
		LivroDao livroDao = new LivroDao().getInstance();
		return livroDao.listaTodosOrdenandoPorAutor();
	}
}
