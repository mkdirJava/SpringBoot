package com.wilson.feelings.tests.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.entities.dto.PersonDTO;
import com.wilson.feelings.rest.endpoints.PersonFeelingsEndPoint;
import com.wilson.feelings.service.person.PersonService;
import com.wilson.feelings.service.person.exception.PersonNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonFeelingsEndPoint.class)
public class RestTests {

	@MockBean
	private PersonService service;
	@Autowired
	private MockMvc mvc;

	private static final String personName = "Bob";
	private static final Long id = 1l;
	private static final Long tooBigId = 23l;
	

	@Before
	public void setupData() {
		Person person = new Person();
		person.setName("TOM");
		PersonDTO personDTO = new PersonDTO();
		personDTO.setPerson(person);
		try {
			when(service.getPersonById(id)).thenReturn(personDTO);
			when(service.getPersonById(tooBigId)).thenThrow(PersonNotFoundException.class);
			
			
		} catch (PersonNotFoundException e) {
			Assert.fail();
			e.printStackTrace();
		}

	}

	@Test
	public void givenIdReturnPerson() throws Exception {
		MvcResult result = mvc.perform(get("/getPerson/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void givenWrongIdReturnError() throws Exception {
		MvcResult result = mvc.perform(get("/getPerson/"+tooBigId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is5xxServerError()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void givenIdShouldDeletePerson() throws Exception{
		MvcResult result = mvc.perform(get("/deletePerson/"+tooBigId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	

}
