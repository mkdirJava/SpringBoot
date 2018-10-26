package com.wilson.feelings.tests.data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;




import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
import com.wilson.feelings.repositories.PersonRepository;
import com.wilson.feelings.util.Utility;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = true)
public class PersonRepositoryTests {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private FeelingRepository feelingRepository;

	private static List<Feeling> feelings = new ArrayList<Feeling>();
	Person person;
	public static final String personName = "HOMER";

	@Before
	public void setupTesting() {

		// reset / create new Person
		person = new Person();
		person.setName(personName);

		feelings.clear();
		Integer max = 10;
		Integer min = 0;

		Random random = new Random();
		IntStream.range(0, 10).forEach(counter -> {
			Feeling feeling = new Feeling();
			Integer feelingRate = random.nextInt(max - min + 1) + min;
			feeling.setDateTime(Utility.dateTimeToDate(LocalDate.now()));
			try {
				feeling.setFeelingRating(feelingRate);
			} catch (FeelingException e) {
				System.out.println("SetUp Issue");
				e.printStackTrace();
			}
			feelings.add(feeling);
		});

	}

	@Test
	public void findPerson() {
		List<Person> persons = personRepository.findAll();
		assertTrue(persons.size() == 5);

		LongStream.range(1, 5).forEach(counter -> {
			Optional<Person> personOption = personRepository.findById(counter);
			if (personOption.isPresent()) {
				Person person = personOption.get();
				assertNotNull(person);
			} else {
				fail("No Reult found");
			}

		});
	}

	@Test
	public void savePerson() {
		personRepository.saveAndFlush(person);
		assertTrue(entityManager.getEntityManager().contains(person));
	}

	@Test
	public void updatePerson() {
		person = personRepository.saveAndFlush(person);
		System.out.println(person.getName());
		List<Feeling> initialFeelings = person.getFeelings();
		Person savedInitial = personRepository.findByName(personName);
		assertTrue(savedInitial.getName().equals(personName));
		System.out.println(savedInitial.getName());

		person.setName("TOD");

		person.setFeelings(feelings);
		System.out.println(person.getId());
		personRepository.saveAndFlush(person);
		Person retrivedPerson = personRepository.findByName("TOD");
		assertTrue(initialFeelings == null);
		assertTrue(savedInitial.getId() == retrivedPerson.getId());
		assertTrue(retrivedPerson.getName().equals("TOD"));

	}

	@Test
	public void deletePerson() {
		List<Person> persons = personRepository.findAll();
		assertTrue(persons.size() == 5);

		persons.stream().forEach(person -> {
			personRepository.delete(person);
		});

		assertTrue(personRepository.findAll().size() == 0);
		assertTrue(feelingRepository.findAll().size() == 0);
		

	}
	

}
