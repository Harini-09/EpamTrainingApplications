package com.epam.springbootdemo.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
public class EntityDB {

	@Bean
	public  EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("my-local-db");
    	return entityManagerFactory.createEntityManager();
    }
}
