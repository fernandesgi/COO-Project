package br.edu.coo2015.ep2.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.entity.Livro;

@Component
public class LivroDaoHibernate implements Serializable  {

	private static final long serialVersionUID = 1L;

	private final Session session;

	public LivroDaoHibernate(Session session) {
		this.session = session;
	}

	public void adiciona(Livro livro) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(livro);
		tx.commit();
	}

	public Livro busca(Livro livro) {
		return (Livro) session.createCriteria(Livro.class)
				.add(Restrictions.eq("titulo", livro.getTitulo()))
				.add(Restrictions.eq("autor", livro.getAutor())).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Livro> buscaPorAutor(String autor) {
		return session.createCriteria(Livro.class)
				.add(Restrictions.eq("autor", autor)).list();
	}

	public void remove(Livro livro) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(livro));
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Livro> listaTodos() {
		List<Livro> list = ((List<Livro>) session.createCriteria(Livro.class).list());
		return list;
	}
}
