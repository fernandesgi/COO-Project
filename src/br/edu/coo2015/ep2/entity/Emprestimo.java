package br.edu.coo2015.ep2.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Emprestimo {
	private long idEmprestador;
	private long idSolicitador;
	private long idLivro;
	
	@Id
	@GeneratedValue
	private long id;
	
	public long getIdEmprestador() {
		return idEmprestador;
	}

	public void setIdEmprestador(long idEmprestador) {
		this.idEmprestador = idEmprestador;
	}

	public long getIdSolicitador() {
		return idSolicitador;
	}

	public void setIdSolicitador(long idSolicitador) {
		this.idSolicitador = idSolicitador;
	}

	public long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(long idLivro) {
		this.idLivro = idLivro;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
