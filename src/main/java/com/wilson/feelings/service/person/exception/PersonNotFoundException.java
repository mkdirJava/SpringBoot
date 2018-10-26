package com.wilson.feelings.service.person.exception;

public class PersonNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(Long personId) {
		super(String.format("Could not Find a Person Id {} ,with exception {}", personId));
	}

}
