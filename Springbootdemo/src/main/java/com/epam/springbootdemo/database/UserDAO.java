package com.epam.springbootdemo.database;


import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.springbootdemo.customexception.InvalidEntryException;
import com.epam.springbootdemo.modal.User;

@Repository
public class UserDAO implements UserDAOOperations {
	private static final Logger LOGGER=LogManager.getLogger(UserDAO.class);

   @Autowired
   private EntityManager entityManager;
   


	public Object viewData(User user) {
	
		 Query query=entityManager.createQuery("select u.userName from User u where u.userName=:username and u.password=:password" );

		 query.setParameter("username",user.getUserName());
		 query.setParameter("password",user.getPassword());
		return query.getSingleResult();

	}
    @Override
	public boolean addData(Object object) {
		boolean flag = false;
		User user =(User)object;
		entityManager.getTransaction().begin();
		try {
			flag = true;
			entityManager.persist(user);
			entityManager.getTransaction().commit();
		} catch (InvalidEntryException e) {
			entityManager.getTransaction().rollback();
			LOGGER.info(e.getMessage());

		} 
		
		return flag;
		
	}

}
