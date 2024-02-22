package database;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.User;
import singletonobjects.UtilityObjects;
 
public class UserDAOJpaImpl implements UserDao {

	private EntityManager entityManager;

	public UserDAOJpaImpl() { 
		this.entityManager = UtilityObjects.getEntityMangerInstance();
	}

	@Override
	public boolean logIn(User user) {
		entityManager.getTransaction().begin();
		TypedQuery<User> query = entityManager.createQuery("from User",User.class);
		entityManager.getTransaction().commit();
		return query.getResultList().contains(user);
	}

	@Override
	public boolean signUp(User user) {
		if (user.getId().isEmpty() || user.getPassword().isEmpty()) {
			return false; 
		}
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return true;
	}

}
