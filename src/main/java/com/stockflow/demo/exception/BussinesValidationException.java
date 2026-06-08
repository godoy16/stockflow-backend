package com.stockflow.demo.exception;

public class BussinesValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BussinesValidationException(String message) {
		super(message);
	}
}
