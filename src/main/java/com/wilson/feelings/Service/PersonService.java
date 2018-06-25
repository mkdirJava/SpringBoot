package com.wilson.feelings.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilson.feelings.DTO.PersonDTO;
import com.wilson.feelings.DTO.ServerToClientDTO;
import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.restEndPoints.exception.PersonNotFoundException;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public PersonDTO getById(Long id) throws PersonNotFoundException {
		
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		Person person = null;
		if(optionalPerson.isPresent()) {
			person = optionalPerson.get();
		}else {
			throw new PersonNotFoundException(id.toString());
		}
		
		PersonDTO personDTO = new PersonDTO();
		personDTO.setPerson(person);
		
		return personDTO;
		
		
		
	}

}
