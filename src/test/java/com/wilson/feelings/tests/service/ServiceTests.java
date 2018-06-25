package com.wilson.feelings.tests.service;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.feelings.Service.PersonService;
import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes=AppConfig.class)
public class ServiceTests {

	
	@TestConfiguration
    static class PersonServiceTestContextConfiguration {
  
        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }
	@Before
	public void setUp() {
	    Person person = new Person();
	    person.setName(name);
	    Mockito.when(personRepository.findByName(name))
	      .thenReturn(person);
	}
	
	
	@Autowired
    private PersonService personService;
 
    @MockBean
    private PersonRepository personRepository;
    
    private static final String name = "Bob";
    private static final Long id = 1l;
    
 
    @Test
    public void whenValidId_thenPersonShouldBeFound() {
        
        Person person = personRepository.findByName(name);
        
        assertEquals(person.getName(),name);
         
          
     }

}
