package com.wilson.feelings.restEndPoints.exception;

public class PersonNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String id) {
		super("Person with ID not found "+ id);
	}

}
