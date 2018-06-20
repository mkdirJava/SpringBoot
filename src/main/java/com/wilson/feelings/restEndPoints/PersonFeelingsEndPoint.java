package com.wilson.feelings.restEndPoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.restEndPoints.exception.PersonNotFoundException;

@RestController
public class PersonFeelingsEndPoint {
	
	@Autowired
	private PersonRepository personRepo;
	
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public Object sent(Exception ex) {
		String result = ex.getLocalizedMessage();
        System.out.println("###########"+result);
        return ex.getClass();
	}
	
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	@RequestMapping("/getPerson/{id}")
	public Person getPerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}else {
			throw new PersonNotFoundException(String.valueOf(id));
		}
		
	}
	
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	@RequestMapping("/deletePerson/{id}")
	public void deletePerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		personRepo.deleteById(id);
		
	}
	
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	@RequestMapping("/updatePerson/{id}")
	public Person updatePerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}else {
			throw new PersonNotFoundException(String.valueOf(id));
		}
		
	}
	
	@RequestMapping("/CreatePerson/{id}")
	@ResponseBody
	public Person createPerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}else {
			throw new PersonNotFoundException(String.valueOf(id));
		}
		
	}
	
}
