package br.edu.coo2015.ep2.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {
	private final Result result;
	
	public IndexController(Result result){
		this.result = result;
	}
	
	public void index(){
		
	}
}
