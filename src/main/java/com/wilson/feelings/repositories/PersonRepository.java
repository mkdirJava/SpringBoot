package com.wilson.feelings.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.wilson.feelings.entities.Person;


@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

	
	Person findByName(String string);

	
}
