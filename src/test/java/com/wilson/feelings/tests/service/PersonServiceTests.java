package com.wilson.feelings.tests.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.entities.dto.PersonDTO;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.rest.dto.UpdatePerson;
import com.wilson.feelings.service.person.PersonService;
import com.wilson.feelings.service.person.PersonServiceImpl;
import com.wilson.feelings.service.person.exception.CreatePersonException;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;
import com.wilson.feelings.service.person.exception.UpdatePersonException;


@RunWith(SpringRunner.class)
public class PersonServiceTests{


    private static final Long id= 1l;
    private static final String name = "bob";
    
	
	@TestConfiguration
    static class PersonServiceTestContextConfiguration {
  
        @Bean
        public PersonServiceImpl personService() {
            return new PersonServiceImpl();
        }
    }
	
	@Before
	public void setUp() {
		
		
	    Person person = new Person();
	    person.setId(1l);
		person.setName(name);
		
		Optional<Person> personOptional = Optional.of(person);
	    Mockito.when(personRepository.findById(id))
	      .thenReturn(personOptional);
	    
		Mockito.when(personRepository.saveAndFlush(person)).thenReturn(person);
		
	}
	
	
	@Autowired
    private PersonService personService;
 
    @MockBean
    private PersonRepository personRepository;
    private PersonDTO personDTO = null;
	private Person person = null;
    
    @Before
    public void setup() {
    	personDTO = new PersonDTO();
    	person = new Person();
    	person.setId(1l);
    	person.setName("TOM");
    	personDTO.setPerson(person);
    }
    
 
    @Test
    public void getPersonById() {
        
    	PersonDTO person = null;
        try {
			person = personService.getPersonById(id);
		} catch (PersonNotFoundException e) {
			Assert.fail();
		}
        assertTrue(person != null);
        assertTrue(person.getPerson()!= null);
        assertTrue(person.getPerson().getName().equals(name));
         
     }
    
    @Test
    public void createPerson() {
    	
    	
    	PersonDTO retrievedPersonDTO = null;
    	
    	try {
			retrievedPersonDTO =personService.createPerson(personDTO);
		} catch (CreatePersonException e) {
			Assert.fail();
			e.printStackTrace();
		}
//    	assertNotNull(retrievedPersonDTO.getPerson());
    	
    };
	
    

}
