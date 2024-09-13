package com.bookhive.ufcg.bookhive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.bookhive.ufcg.bookhive")
@EnableJpaRepositories(basePackages = "com.bookhive.ufcg.bookhive")
public class BookhiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookhiveApplication.class, args);
	}

}
