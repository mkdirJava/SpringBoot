package com.wilson.feelings.service.person;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.entities.dto.PersonDTO;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.rest.dto.UpdatePerson;
import com.wilson.feelings.service.person.exception.CreatePersonException;
import com.wilson.feelings.service.person.exception.DeletePersonException;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;
import com.wilson.feelings.service.person.exception.UpdatePersonException;

@Component
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) throws CreatePersonException {
		Person person = personRepository.saveAndFlush(personDTO.getPerson());
		personDTO.setPerson(person);
		return personDTO;
	}

	@Override
	public PersonDTO getPersonById(Long id) throws PersonNotFoundException {
		Optional<Person> optionalPerson = personRepository.findById(id);

		Person person = null;
		if (optionalPerson.isPresent()) {
			person = optionalPerson.get();
		} else {
			throw new PersonNotFoundException(id);
		}

		PersonDTO personDTO = new PersonDTO();
		personDTO.setPerson(person);

		return personDTO;
	}

	@Override
	public PersonDTO updatePerson(PersonDTO personDTO, UpdatePerson updatePerson) throws UpdatePersonException {
		//Wl Find the person first then user the DTO to update and then merge in again
		personDTO.getPerson().getId();
//		Person person = personRepository.saveAndFlush();
		updatePerson.getProperty();
		
		
//		personDTO.setPerson(person);
		return personDTO;
	}

	@Override
	public void deletePersonById(Long id) throws DeletePersonException {
		if(personRepository.findById(id) == null)
			throw new DeletePersonException(String.format("The id does not exsist %s", id));
		personRepository.deleteById(id);
	}


	

}
