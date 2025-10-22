package com.dtdc.demo_desk.utility;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Utility {
	public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pgsql");
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();
	public static EntityTransaction entityTransaction = entityManager.getTransaction();
	
//	static {
//		
//		
//	}

}
