package br.edu.coo2015.ep2.controller;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.edu.coo2015.ep2.dao.LivroDaoHibernate;
import br.edu.coo2015.ep2.entity.Livro;

@Resource
public class LivroController {
	
	private final Result result;
	private final Validator validator;
	private final LivroDaoHibernate livroDaoHibernate;
	
	public LivroController(LivroDaoHibernate livroDao, Result result, Validator validator){
		this.result = result;
		this.validator = validator;
		this.livroDaoHibernate = livroDao;
	}
	
	public List<Livro> lista() {
        return livroDaoHibernate.listaTodos();
    }
	
	public void pedir(Livro livro){
		//fazPedido();
		result.redirectTo(LivroController.class).lista();
	}
}
