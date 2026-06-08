package com.stockflow.demo.exception;

public class ProductNotFoudException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductNotFoudException(String message) {
		super(message);
	}

}
