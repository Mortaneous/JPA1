package com.mortaneous.udemy.jpa1;

import javax.persistence.EntityManager;

public class Logger {
	EntityManager entityManager;

	public Logger(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void log(String message) {
		SystemLog sysLog = new SystemLog(message);
		
		entityManager.getTransaction().begin();
		entityManager.persist(sysLog);
		entityManager.getTransaction().commit();
	}
}
