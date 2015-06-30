package br.edu.coo2015.ep2.model;

import java.util.List;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.entity.Livro;

@Component
public class BibliotecaCompartilhadaFacade {

	private LivroDaoHibernate livroDaoHibernate;
	
	public void setLivroDao(LivroDaoHibernate livroDaoHibernate) {
		this.livroDaoHibernate = livroDaoHibernate;
	}
	
	public List<Livro> listaTodosLivros() {
		List<Livro> lista = (List<Livro>) livroDaoHibernate.listaTodos();
		return lista;
	}
}