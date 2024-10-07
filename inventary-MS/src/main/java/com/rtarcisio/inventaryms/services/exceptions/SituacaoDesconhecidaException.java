package com.rtarcisio.inventaryms.services.exceptions;

public class SituacaoDesconhecidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String situacao;
	private final String identificador;

	public SituacaoDesconhecidaException(String msg, String situacao, String identificador) {
		super(msg);
		this.situacao = situacao;
		this.identificador = identificador;
	}

	public String getSituacao() {
		return situacao;
	}

	public String getIdentificador() {
		return identificador;
	}

}
