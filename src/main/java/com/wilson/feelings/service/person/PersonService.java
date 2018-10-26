package com.wilson.feelings.service.person;


import org.springframework.stereotype.Service;

import com.wilson.feelings.entities.dto.PersonDTO;
import com.wilson.feelings.rest.dto.UpdatePerson;
import com.wilson.feelings.service.person.exception.CreatePersonException;
import com.wilson.feelings.service.person.exception.DeletePersonException;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;
import com.wilson.feelings.service.person.exception.UpdatePersonException;

@Service
public interface PersonService {
	
	public PersonDTO createPerson(PersonDTO person) throws CreatePersonException;
	
	public PersonDTO getPersonById(Long id) throws PersonNotFoundException;
	
	public PersonDTO updatePerson(PersonDTO personDTO, UpdatePerson updatePerson) throws UpdatePersonException;
	
	public void deletePersonById(Long id) throws DeletePersonException;
	
}
