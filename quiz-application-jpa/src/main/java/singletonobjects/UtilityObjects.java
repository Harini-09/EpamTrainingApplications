package singletonobjects;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityObjects {
	
	private static UtilityObjects objs;

	private UtilityObjects() {
		
		
	}
	
	public static UtilityObjects getUtilityObjectsInstance() {
		if(objs==null) {
			return new UtilityObjects();
		}
		return objs;
	}

	private static EntityManagerFactory efactory = Persistence.createEntityManagerFactory("my-local-quiz-db");
	private static EntityManager entityManager=null;
	private static Logger LOGGER;
	private static Scanner scanner;

	public static EntityManager getEntityMangerInstance() {
		if (entityManager == null) {
			entityManager = efactory.createEntityManager();
		}
		return entityManager;
	}

	public static Scanner getScannerInstance() {
		if (scanner == null) {
			scanner = new Scanner(System.in);
		}
		return scanner;
	}

	public static Logger getLoggerInstance() {
		if (LOGGER == null) {
			LOGGER = LogManager.getLogger(UtilityObjects.class);
		}
		return LOGGER;
	}
}
