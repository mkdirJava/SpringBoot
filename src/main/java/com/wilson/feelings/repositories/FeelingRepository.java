package com.wilson.feelings.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wilson.feelings.entities.Feeling;


@Transactional
public interface FeelingRepository extends JpaRepository<Feeling, Long> {

	
}
