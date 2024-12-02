package com.maternease.maternease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaterneaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterneaseApplication.class, args);
	}

}
