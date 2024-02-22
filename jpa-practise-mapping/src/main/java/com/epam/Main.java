package com.epam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.epam.entities.Address;
import com.epam.entities.AddressDemo;
import com.epam.entities.User;
import com.epam.entities.UserRefactor;

public class Main{
	public static void main(String[] args) {
		EntityManagerFactory efactory = Persistence.createEntityManagerFactory("my-local-user-db");
		EntityManager entityManager = efactory.createEntityManager();
		
		Address address1 = new Address();
		address1.setFlatNo(207);
		address1.setApartmentName("ucon");
		address1.setCity("kurnool");
		
		Address address2 = new Address();
		address2.setFlatNo(208);
		address2.setApartmentName("ucon");
		address2.setCity("kurnool");
		
//		address1.setAddressId(1);
//		address2.setAddressId(2);
		
		User user1 = new User("Harini","harini.gundampati@gmail.com");
		User user2 = new User("Harshi","harshi.gundampati@gmail.com");
		
		List<Address> addresses1 = List.of(address1,address2);
		List<Address> addresses2 = List.of(address1);

		
		entityManager.getTransaction().begin();
		entityManager.persist(address1);
		entityManager.persist(address2);
		user1.setAddresses(addresses1);
		user2.setAddresses(addresses2);
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.getTransaction().commit();
		
		
		

//		User user1 = new User("Harini","harini.gundampati@gmail.com");
//		User user2 = new User("Harshi","harshi.gundampati@gmail.com");
//		
//		Address address1 = new Address();
//		address1.setFlatNo(207);
//		address1.setApartmentName("ucon");
//		address1.setCity("kurnool");
//		
//		Address address2 = new Address();
//		address2.setFlatNo(208);
//		address2.setApartmentName("ucon");
//		address2.setCity("kurnool");
		
	//	List<User> users = new ArrayList<>();
		
		
		
//		entityManager.getTransaction().begin();
//		user1 = entityManager.find(User.class,1);
//		entityManager.remove(user1);
//		entityManager.getTransaction().commit();
		
		
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*		User user1=new User();
		user1.setUserName("Harini");
		user1.setEmail("harini@gmail.com");
		
		//user1.setAddress(address1);
		User user2=new User();
		user2.setUserName("Harshini");
		user2.setEmail("harshi@gmail.com");
		
		User user3=new User();
		user3.setUserName("Divya");
		user3.setEmail("divya@gmail.com");
		
//		entityManager.getTransaction().begin();
//		entityManager.persist(user1);
//		entityManager.persist(user2);
//		entityManager.persist(user3);
//		entityManager.getTransaction().commit();
		
		//user3.setUserId(3);
		
		List<User> usersList1 = new ArrayList();
		//user1.setUserId(1);
		usersList1.add(user1);
		//user2.setUserId(2);
		usersList1.add(user2);
		
		Address address1 = new Address(101,"ucon","Kurnool");
		address1.setUsers(usersList1);
		
		List<User> usersList2 = new ArrayList();
		usersList2.add(user2);
		usersList2.add(user3);
		Address address2 = new Address(201,"ucon","Kurnool");
		address2.setUsers(usersList2);
		//user2.setAddress(address2);
//		Address address3 = new Address(701,"ucon","Kurnool");
//		User user3=new User();
//		user3.setUserName("Harshini");
//		user3.setEmail("harshi@gmail.com");
//		user3.setAddress(address3);
		entityManager.getTransaction().begin();
//		entityManager.persist(user1);
//		entityManager.persist(user2);
//		entityManager.persist(user3);
		//entityManager.persist(address1);
		//entityManager.persist(address2);
		//Address returnedAddress=entityManager.find(Address.class,2);
		User returnedUser=entityManager.find(User.class, 2);
		//System.out.println(returnedUser);
		//returnedUser.setAddress(null);
		entityManager.remove(returnedUser);
		entityManager.getTransaction().commit();
//		System.out.println(address1);
//		System.out.println(address2);
//		System.out.println(user1);
//		System.out.println(user2);  */
	