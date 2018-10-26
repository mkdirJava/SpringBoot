package com.wilson.feelings.service.person.exception;

import com.wilson.feelings.entities.dto.PersonDTO;

public class DeletePersonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1941335466064258302L;

	public DeletePersonException(PersonDTO person, Exception e) {
		super(String.format("Could not Delete a Person with details {} ,with exception {}", person, e));
	}
	
	public DeletePersonException(String message) {
		super(message);
	}
}
