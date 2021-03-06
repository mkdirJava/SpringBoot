package com.wilson.feelings.test.integration;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.service.person.PersonServiceImpl;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;

import ch.qos.logback.core.net.SyslogOutputStream;



@RunWith(SpringRunner.class)
@SpringBootTest(value="SpringBootTest.WebEnvironment.MOCK")
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-integration-test.properties")
public class IntegrationTest {

	@Autowired
    private MockMvc mvc;
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired 
	private PersonServiceImpl personSerivce;
	
	@Test
	public void test() {
		System.out.println("#####################################");
		Person person = null;
		try {
			person =personSerivce.getPersonById(1l).getPerson();
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(person);
		System.out.println(person.getName());
		assertEquals(person.getName(), "Tom");
		System.out.println("#####################################");
	}

}
