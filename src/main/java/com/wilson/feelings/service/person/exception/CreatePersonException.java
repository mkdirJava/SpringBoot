package com.wilson.feelings.service.person.exception;

import com.wilson.feelings.entities.dto.PersonDTO;

public class CreatePersonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024616562742472965L;

	public CreatePersonException(PersonDTO person, Exception e) {
		super(String.format("Could not Create a Person with details {} ,with exception {}", person, e));
	}
}
