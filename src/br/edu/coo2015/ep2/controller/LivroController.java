package br.edu.coo2015.ep2.controller;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.edu.coo2015.ep2.dao.LivroDao;
import br.edu.coo2015.ep2.entity.Livro;

@Resource
public class LivroController {
	
	private LivroDao LivroDao;
	private Result result;
	private Validator validator;
	
	public LivroController(LivroDao livroDao, Result result, Validator validator){
		this.LivroDao = LivroDao;
		this.result = result;
		this.validator = validator;
	}
	
	public List<Livro> lista() {
        return LivroDao.listaTodos();
    }
	
	public void adiciona(Livro livro){
		LivroDao.adiciona(livro);
		result.redirectTo(LivroController.class).lista();
	}
}
