package com.wilson.feelings.tests.web;

import java.util.Arrays;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.wilson.feelings.DTO.PersonDTO;
import com.wilson.feelings.Service.PersonService;
import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.restEndPoints.PersonFeelingsEndPoint;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@RunWith(SpringRunner.class)
@WebMvcTest(PersonFeelingsEndPoint.class)
public class RestTests {
	
	@MockBean
    private PersonService service;
	
	
	@Autowired
    private MockMvc mvc;
 
    
    
    
    private static final String personName ="Bob";
    private static final Long id = 0l;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
      throws Exception {
         
    	Person person = new Person();
        PersonDTO personDTO = new PersonDTO();
        
     
        
        when(service.getById(id)).thenReturn(personDTO);
     
        mvc.perform( get("/getPerson/1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().is2xxSuccessful());
    }
}
