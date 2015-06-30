package br.edu.coo2015.ep2.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
public class HibernateSessionCreator implements ComponentFactory<Session>, Serializable {

	private static final long serialVersionUID = 1L;

	private final SessionFactory factory;
	private Session session;

	public HibernateSessionCreator(SessionFactory factory) {
		this.factory = factory;
	}

	@PostConstruct
	public void abre() {
		this.session = factory.openSession();
	}

	@Override
	public Session getInstance() {
		return this.session;
	}

	@PreDestroy
	public void fecha() {
		this.session.close();
	}

}
