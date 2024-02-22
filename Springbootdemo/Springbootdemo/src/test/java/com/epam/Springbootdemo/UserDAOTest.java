package com.epam.Springbootdemo;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.springbootdemo.customexception.InvalidEntryException;
import com.epam.springbootdemo.database.UserDAO;
import com.epam.springbootdemo.modal.User;

@ExtendWith(MockitoExtension.class)
 class UserDAOTest {
	@Mock
	EntityManager entityManager;
	@Mock
	TypedQuery<User> query;
	@Mock
	EntityTransaction transaction;
	
	@InjectMocks
	UserDAO userDAO;
	
	@Test
	void viewDataIFIDPresent() {
		User user=new User(1,"Admin","Admin","Admin","Admin@gmail.com");
		Object actual=user;
		Query query = Mockito.mock(Query.class); 
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);

		
		Object expected=userDAO.viewData(user);
		assertEquals(expected,actual);
		
		
	}
	@Test
	void AddQuestionIFIDnotPresent() {
		User user=new User(1,"Admin","Admin","Admin","Admin@gmail.com");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.doNothing().when(entityManager).persist(user);
	    
		boolean expected=userDAO.addData(user);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).persist(user);
	

        assertEquals(true,expected);
 

		

	}
	@Test
	void dontAddQuestionIFIDnPresent() {
		User user=new User(1,"Admin","Admin","Admin","Admin@gmail.com");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.doThrow(InvalidEntryException.class).when(entityManager).persist(user);
		userDAO.addData(user);
		Mockito.verify(entityManager,times(2)).getTransaction();
		Mockito.verify(entityManager).persist(user);	

	}
}
