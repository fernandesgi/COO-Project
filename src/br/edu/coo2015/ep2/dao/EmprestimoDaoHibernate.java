package br.edu.coo2015.ep2.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.edu.coo2015.ep2.entity.Emprestimo;

@Component
public class EmprestimoDaoHibernate implements Serializable  {

	private static final long serialVersionUID = 1L;

	private final Session session;

	public EmprestimoDaoHibernate(Session session) {
		this.session = session;
	}

	public void adiciona(Emprestimo emp) {
		Transaction tx = this.session.beginTransaction();
		this.session.save(emp);
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	public List<Emprestimo> buscaEmprestados() {
		return (List<Emprestimo>) session.createCriteria(Emprestimo.class).list();
	}
}
