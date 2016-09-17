package com.services.addressmate.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String errorMessage){
		super(errorMessage);
	}
}
