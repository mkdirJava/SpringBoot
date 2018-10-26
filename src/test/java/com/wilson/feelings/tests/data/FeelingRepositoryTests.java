package com.wilson.feelings.tests.data;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.feelings.entities.Feeling;
import com.wilson.feelings.entities.Person;
import com.wilson.feelings.entities.exception.FeelingException;
import com.wilson.feelings.repositories.FeelingRepository;
import com.wilson.feelings.service.feeling.exception.FeelingNotFoundException;
import com.wilson.feelings.util.Utility;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class FeelingRepositoryTests {

	@Autowired
	private FeelingRepository feelingRepository;
	@Autowired
	private TestEntityManager entityManager;
	private static Feeling feeling = null;
	
	@Before
	public void setup() {
		
		Optional<Feeling> feelingOptional = feelingRepository.findById(1l);
		if(feelingOptional.isPresent()) {
			feeling = feelingOptional.get();
			Person person = feeling.getPerson();
			assertTrue(person != null);
			System.out.println(person );
		}else {
			Assert.fail();
		}
	}
	
	@Test 
	public void testFeelingValidationTooLarge() {
		Feeling feeling = new Feeling();
		try {
			feeling.setFeelingRating(12);
		} catch (FeelingException e) {
			return;
		}
		Assert.fail();
		
	}
	@Test
	public void testFeelingValidationTooSmall() {
		Feeling feeling = new Feeling();
		try {
			feeling.setFeelingRating(-12);
		} catch (FeelingException e) {
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void testFeelingValidationJustRight() {
		Feeling feeling = new Feeling();
		try {
			feeling.setFeelingRating(5);
		} catch (FeelingException e) {
			Assert.fail();
		}
		
	}
	
	@Test
	public void createFeeling() {
		assertTrue(feelingRepository.findAll().size() == 5);
		Feeling feeling = new Feeling();
		feeling.setDateTime(Utility.dateTimeToDate(LocalDate.now()));
		feelingRepository.save(feeling);
		assertTrue(feeling.getId() != null);
		assertTrue(entityManager.getEntityManager().contains(feeling));
		assertTrue(feelingRepository.findAll().size() == 6);
		
	}
	
	@Test
	public void findFeeling() {
		Optional<Feeling> feelingOptional = feelingRepository.findById(1l);
		if(feelingOptional.isPresent()) {
			Feeling feeling = feelingOptional.get();
			assertTrue(feeling.getPerson() != null);
			System.out.println(feeling.getPerson() );
		}else {
			Assert.fail();
		}
		
	}
	
	@Test
	public void updateFeeling() {
		
		Integer initialFeeling = feeling.getFeelingRating();
		try {
			feeling.setFeelingRating(2);
		} catch (FeelingException e) {
			Assert.fail();
			e.printStackTrace();
		}
		
		assertTrue(initialFeeling != feeling.getFeelingRating());
		
	}
	
	@Test
	public void deleteFeeling() {
		feelingRepository.delete(feeling);
		assertFalse(entityManager.getEntityManager().contains(feeling));
	}
	

}
