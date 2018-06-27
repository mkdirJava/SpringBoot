package com.wilson.feelings.restEndPoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.feelings.Service.PersonService;
import com.wilson.feelings.annotations.LogExecutionTime;
import com.wilson.feelings.restEndPoints.exception.PersonNotFoundException;

@RestController
public class PersonFeelingsEndPoint {
	
	@Autowired
	private PersonService personService;
	
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public Object sent(Exception ex) {
		String result = ex.getLocalizedMessage();
        System.out.println("###########"+result);
        return ex.getClass();
	}
	
	@LogExecutionTime
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	@RequestMapping(value="/getPerson/{id}",method=RequestMethod.GET)
	public String getPerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		if(id >10)
			throw new PersonNotFoundException(id.toString());
		return "Hi";
	}
	
//	@ResponseStatus(code=HttpStatus.ACCEPTED)
//	@RequestMapping("/deletePerson/{id}")
//	public void deletePerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
//	{
//		personRepo.deleteById(id);
		
//	}
	
//	@ResponseStatus(code=HttpStatus.ACCEPTED)
//	@RequestMapping("/updatePerson/{id}")
//	public Person updatePerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
//	{
//		Optional<Person> optionalPerson = personRepo.findById(id);
//		if(optionalPerson.isPresent()) {
//			return optionalPerson.get();
//		}else {
//			throw new PersonNotFoundException(String.valueOf(id));
//		}
		
//	}
//	
//	@RequestMapping("/CreatePerson/{id}")
//	@ResponseBody
//	public Person createPerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
//	{
//		Optional<Person> optionalPerson = personRepo.findById(id);
//		if(optionalPerson.isPresent()) {
//			return optionalPerson.get();
//		}else {
//			throw new PersonNotFoundException(String.valueOf(id));
//		}
		
//	}
	
}
