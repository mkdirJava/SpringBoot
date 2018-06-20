package com.wilson.feelings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EntityScan(basePackages = {"com.wilson.feelings.entities"})
public class FeelingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeelingsApplication.class, args);
	}
}
