package br.edu.coo2015.ep2.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.entity.Livro;
import br.edu.coo2015.ep2.entity.Usuario;

@Component
public class UsuarioDaoHibernate implements Serializable  {

	private static final long serialVersionUID = 1L;

	private final Session session;

	public UsuarioDaoHibernate(Session session) {
		this.session = session;
	}

	public void adiciona(Usuario usuario) throws Exception {
		Usuario usuarioBD = busca(usuario);
		if (usuarioBD == null) {
			Transaction tx = this.session.beginTransaction();
			this.session.save(usuario);
			tx.commit();
			return;
		}
		throw new Exception("Email já cadastrado no sistema!");
	}

	public Usuario busca(Usuario usuario) {
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("login", usuario.getLogin())).uniqueResult();
	}

	public void remove(Usuario usuario) {
		Transaction tx = session.beginTransaction();
		session.delete(busca(usuario));
		tx.commit();
	}
	
	public void update(Usuario usuario) {
		Transaction tx = session.beginTransaction();
		session.update(usuario);
		tx.commit();
	}

}
