package br.edu.coo2015.ep2.model;

public class AutenticacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public AutenticacaoException() {
	}
	
	public AutenticacaoException(String mensagem) {
		super(mensagem);
	}
}
