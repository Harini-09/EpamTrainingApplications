package singletonobjects;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityObjects {
	
	private UtilityObjects() {
		
	}
	private static final EntityManagerFactory efactory = Persistence.createEntityManagerFactory("my-local-quiz-db");
	private static final EntityManager entityManager = efactory.createEntityManager();
	private static final Logger LOGGER = LogManager.getLogger(UtilityObjects.class);
	private static final Scanner scanner = new Scanner(System.in);
	
	public static EntityManager getEntityManagerInstance() {
		return entityManager;
	}
	
	public static Scanner getScannerInstance() {
		return scanner;
	}
	
	public static Logger getLoggerInstance() {
		return LOGGER;
	}
}
