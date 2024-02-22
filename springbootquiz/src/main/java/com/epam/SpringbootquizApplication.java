package com.epam;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringbootquizApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootquizApplication.class, args);
	}

	@Bean
	public EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-local-quiz-db");
		return emf.createEntityManager();
	}

	@Bean
	public Scanner getScanner() {
		return new Scanner(System.in);
	}
}
