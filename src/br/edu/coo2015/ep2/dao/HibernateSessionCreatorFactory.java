package br.edu.coo2015.ep2.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@SuppressWarnings("deprecation")
@Component
public class HibernateSessionCreatorFactory implements ComponentFactory<SessionFactory>, Serializable {

	private static final long serialVersionUID = 1L;

	private SessionFactory factory;

	@PostConstruct
	public void abre() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		this.factory = configuration.buildSessionFactory();
	}

	@Override
	public SessionFactory getInstance() {
		return this.factory;
	}

	@PreDestroy
	public void fecha() {
		this.factory.close();
	}
}