package com.promad.msi.listaplacas.errorhandling;

public class InvalidRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883283395096400811L;


	public InvalidRequestException(String message) {
		super(message);
	}

	public InvalidRequestException(Throwable throwable) {
		super(throwable);
	}

}
