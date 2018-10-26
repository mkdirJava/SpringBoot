package com.wilson.feelings.service.person.exception;

import com.wilson.feelings.entities.dto.PersonDTO;

public class UpdatePersonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7928993747883690124L;

	public UpdatePersonException(PersonDTO person, Exception e) {
		super(String.format("Could not Update a Person with details {} ,with exception {}", person, e));
	}
}
