package com.promad.msi.listaplacas.errorhandling;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883283395096400811L;


	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable throwable) {
		super(throwable);
	}

}
