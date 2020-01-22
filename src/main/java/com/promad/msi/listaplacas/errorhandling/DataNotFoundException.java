package com.promad.msi.listaplacas.errorhandling;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7883283395096400811L;


	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable throwable) {
		super(throwable);
	}

}
