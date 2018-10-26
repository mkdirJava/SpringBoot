package com.wilson.feelings.tests.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.feelings.entities.Feeling;
import com.wilson.feelings.entities.dto.FeelingDTO;
import com.wilson.feelings.entities.exception.FeelingException;
import com.wilson.feelings.repositories.FeelingRepository;
import com.wilson.feelings.service.feeling.FeelingService;
import com.wilson.feelings.service.feeling.FeelingServiceImpl;
import com.wilson.feelings.service.feeling.exception.FeelingNotFoundException;

@RunWith(SpringRunner.class)
public class FeelingServiceTests {
	
	@TestConfiguration
    static class PersonServiceTestContextConfiguration {
  
        @Bean
        public FeelingServiceImpl feelingService() {
            return new FeelingServiceImpl();
        }
    }

	@Autowired
	private FeelingService feelingService;
	@MockBean
	private FeelingRepository feelingRepository;
	private static final Long id =1l;
	
	private FeelingDTO feelingDTO = null;
	private Feeling feeling = null;
	private Optional<Feeling> feelingOptional;
	
	
	@Before
	public void setup() {
		feelingDTO = new FeelingDTO();
		feeling = new Feeling();
		feelingDTO.setFeeling(feeling);
		
		feelingOptional = Optional.of(feeling);
		
		Mockito.when(feelingRepository.saveAndFlush(feeling)).thenReturn(feeling);
		Mockito.when(feelingRepository.findById(id)).thenReturn(feelingOptional);
	}
	
	
	
	@Test
	public void createFeeling(){
		FeelingDTO retrievedFeelingDTO = feelingService.createFeeling(feelingDTO);
		assertTrue(retrievedFeelingDTO.equals(feelingDTO));
	};
	
	@Test
	public void getFeelingById(){
		FeelingDTO retrievedDTO = null;
		try {
			retrievedDTO = feelingService.getFeelingById(id);
			if(retrievedDTO.getFeeling() == null) {
				fail("get feeling by id should return with somthing");
			}
		} catch (FeelingNotFoundException e) {
			fail();
			e.printStackTrace();
		}
		Feeling knownFeeling =feelingOptional.get();
		assertTrue(retrievedDTO.getFeeling().equals(knownFeeling));
		
	};
	
	@Test
	public void updateFeeling() {
		FeelingDTO retrievedFeelingDTO = null;
		Feeling retrievedFeeling = null;
		Integer retrievedFeelingRate = null;
		try {
			retrievedFeelingDTO = feelingService.getFeelingById(id);
			retrievedFeeling = retrievedFeelingDTO.getFeeling();
			retrievedFeelingRate = retrievedFeeling.getFeelingRating();
		} catch (FeelingNotFoundException e) {
			fail();
			e.printStackTrace();
		}
		
		try {
			retrievedFeeling.setFeelingRating(9);
		} catch (FeelingException e) {
			fail();
			e.printStackTrace();
		}
		retrievedFeelingDTO.setFeeling(retrievedFeeling);
		
		FeelingDTO updatedFeelingDTO = feelingService.updateFeeling(feelingDTO);
		
		assertFalse(updatedFeelingDTO.getFeeling().getFeelingRating() == retrievedFeelingRate);
		
		
		
		

	};

}
