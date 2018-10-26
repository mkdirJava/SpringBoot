package com.wilson.feelings.rest.dto;

import com.wilson.feelings.entities.dto.PersonDTO;

public class ServerToClientDTO {
	
	private PersonDTO personDTO;
	private Exception exception;
	
	public PersonDTO getPersonDTO() {
		return personDTO;
	}
	public void setPersonDTO(PersonDTO personDTO) {
		this.personDTO = personDTO;
	}
	
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	

}
