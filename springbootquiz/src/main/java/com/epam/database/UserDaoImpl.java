package com.epam.database;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.entities.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean logIn(User user) {
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager.createQuery("from User", User.class);
		entityManager.getTransaction().commit();
		return query.getResultList().contains(user);
	}

	@Override
	public boolean signUp(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return true;
	}

}
