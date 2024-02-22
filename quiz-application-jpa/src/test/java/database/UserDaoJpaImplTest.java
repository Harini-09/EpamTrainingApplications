package database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import model.User;

@ExtendWith(MockitoExtension.class)
class UserDaoJpaImplTest {
	
	@Mock
	private EntityManager entityManager;
	
	@Mock
	private EntityTransaction transaction;
	
	@InjectMocks
	private UserDAOJpaImpl userDao;
	
	@Mock
	private TypedQuery<User> query;

	@Test
	void logInTest() { 
		User user = new User("Star","World","admin");
		List<User> users = Arrays.asList(user);
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		Mockito.when(entityManager.createQuery("from User", User.class)).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(users);

		boolean isValidUser = userDao.logIn(user);
		assertEquals(true, isValidUser);
	}
	
	@Test
	void signUpTest() {
		User user = new User("Star","World","admin");
		Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(entityManager).persist(user);
		boolean isSignedUp = userDao.signUp(user);
		assertEquals(true,isSignedUp);
	}
	 
} 
