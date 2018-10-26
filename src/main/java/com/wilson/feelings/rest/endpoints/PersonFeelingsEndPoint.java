package com.wilson.feelings.rest.endpoints;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.feelings.annotations.LogExecutionTime;
import com.wilson.feelings.entities.Person;
import com.wilson.feelings.entities.dto.PersonDTO;
import com.wilson.feelings.rest.dto.ServerToClientDTO;
import com.wilson.feelings.service.person.PersonService;
import com.wilson.feelings.service.person.exception.DeletePersonException;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;
import com.wilson.feelings.service.person.exception.UpdatePersonException;

@RestController
public class PersonFeelingsEndPoint {

	private static Logger logger = LogManager.getLogger(PersonFeelingsEndPoint.class);

	@Autowired
	private PersonService personService;

	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public Object sent(Exception ex) {
		String result = ex.getLocalizedMessage();
		System.out.println("###########" + result);
		return ex.getClass();
	}

	@LogExecutionTime
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	@GetMapping(value = "/getPerson/{id}")
	public ResponseEntity<ServerToClientDTO> getPerson(@PathVariable(value = "id") Long id) throws PersonNotFoundException{
		ServerToClientDTO serverToClientDTO = new ServerToClientDTO();

		serverToClientDTO.setPersonDTO(personService.getPersonById(id));

		return new ResponseEntity<ServerToClientDTO>(serverToClientDTO, HttpStatus.CREATED);
	}

	@GetMapping("/deletePerson/{id}")
	public ResponseEntity<ServerToClientDTO> deletePerson(@PathVariable(value = "id") Long id)
			throws PersonNotFoundException, DeletePersonException {
		personService.deletePersonById(id);		
		return new ResponseEntity<ServerToClientDTO>(HttpStatus.ACCEPTED);

	}

	@GetMapping("/updatePerson/{id}")
	public ResponseEntity<ServerToClientDTO> updatePerson(@PathVariable(value = "id") Long id)
			throws PersonNotFoundException, UpdatePersonException {
		PersonDTO personDTO = personService.getPersonById(id);
		Person person = personDTO.getPerson();
//		if(person != null)
			
//		personService.updatePerson(personDTO);
		
//		Optional<Person> optionalPerson = personRepo.findById(id);
//		if(optionalPerson.isPresent()) {
//			return optionalPerson.get();
//		}else {
//			throw new PersonNotFoundException(String.valueOf(id));
//		}
//		
		return null;
	}
	
	@GetMapping("/createPerson/{id}")
	public ResponseEntity<ServerToClientDTO> createPerson(@PathVariable(value = "id") Long id)
			throws PersonNotFoundException {
//		Optional<Person> optionalPerson = personRepo.findById(id);
//		if(optionalPerson.isPresent()) {
//			return optionalPerson.get();
//		}else {
//			throw new PersonNotFoundException(String.valueOf(id));
//		}
		return null;

	}

}
