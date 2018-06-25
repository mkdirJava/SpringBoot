package com.wilson.feelings.tests.data;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.feelings.entities.Person;
import com.wilson.feelings.repositories.PersonRepository;

@RunWith(SpringRunner.class)
// @SpringBootTest(classes=AppConfig.class)
@DataJpaTest(showSql = true)
public class DAOTests {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PersonRepository personRepository; 

	@Test
	public void contextLoads() {
		System.out.println(personRepository.count());
		Person person = new Person();
		person.setName("HOMER");
		entityManager.persist(person);
		
		assertTrue(entityManager.getEntityManager().contains(person));
	}

}
