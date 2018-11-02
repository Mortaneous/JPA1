package com.mortaneous.jpa1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("com.mortaneous.jpa1");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();
	private static Logger logger = new Logger(entityManager);

	public static void main(String[] args) {
		Person p = new Person("John Doe", 37);
		Person p2 = new Person("Jane Doe", 35);
		Person p3 = new Person("Mr. Smith", 44);

		create(p);
		read(1);
		read(2);
		create(p2);
		read(2);
		create(p3);

		entityManager.close();
		entityManagerFactory.close();
	}

	public static void create(Person newPerson) {
		entityManager.getTransaction().begin();
		
		entityManager.persist(newPerson);
		
		entityManager.getTransaction().commit();
		logger.log("create()::added entry - " + newPerson.toString());
	}

	public static Person read(int id) {
		Person p = entityManager.find(Person.class, id);
		StringBuffer log = new StringBuffer("read()::");

		if (p != null) {
			log.append("retrieved entry - " + p.toString());
		} else {
			log.append("Id " + id + " not found.");
		}
		
		logger.log(log.toString());
		
		return p;
	}

}
