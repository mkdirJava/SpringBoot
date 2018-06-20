package com.wilson.feelings.restEndPoints;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.restEndPoints.exception.PersonNotFoundException;

@Controller
public class PersonFeelingsEndPoint {
	
	@Autowired
	private PersonRepository personRepo;
	
	@RequestMapping("/hi")
	public ModelAndView sent() {
		return new ModelAndView("home");
	}
	
	@RequestMapping("/getPerson/{id}")
	@ResponseBody
	public Person getPerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}else {
			throw new PersonNotFoundException(String.valueOf(id));
		}
		
	}
	
	@RequestMapping("/deletePerson/{id}")
	@ResponseBody
	public Person deletePerson(@PathVariable(value="id") Long id) throws PersonNotFoundException 
	{
		Optional<Person> optionalPerson = personRepo.findById(id);
		if(optionalPerson.isPresent()) {
			return optionalPerson.get();
		}else {
			throw new PersonNotFoundException(String.valueOf(id));
		}
		
	}
	
	@RequestMapping("/updatePerson/{id}")
	@ResponseBody
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
	
	
//	
//	@RequestMapping("/error")
//	@ResponseBody
//	public String error() {
//		return "hi";
//		
//	}

	
}
