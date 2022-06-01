package br.com.fiap.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;

public class UserDAO {

	@Inject
	private EntityManager manager;
	
	public void create(User user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.clear();
	}
	
	public List<User> listAll() {
		return manager.createQuery("SELECT u FROM User u", User.class).getResultList();
	}

	public boolean exist(User user) {
		
		String sql = "SELECT u FROM User u WHERE "
					+ "email=:email AND "
					+ "password=:password";
		TypedQuery<User> query = manager.createQuery(sql , User.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		
		try {
			query.getSingleResult();
			return true;
		} catch (Exception e) {
			return false;
		}
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
