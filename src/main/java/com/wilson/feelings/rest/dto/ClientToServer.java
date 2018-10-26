package com.wilson.feelings.rest.dto;

public class ClientToServer {
	
	private UpdatePerson updatePerson;
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UpdatePerson getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(UpdatePerson updatePerson) {
		this.updatePerson = updatePerson;
	}
	

}
