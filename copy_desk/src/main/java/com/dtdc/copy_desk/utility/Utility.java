package com.dtdc.copy_desk.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Utility {
	
	public static String currentDate;
	static EntityManagerFactory  entityManagerFactory= Persistence.createEntityManagerFactory("pgsql");
	public static EntityManager entityManager = entityManagerFactory.createEntityManager();
	public static EntityTransaction entityTransaction = entityManager.getTransaction();
	static {
		LocalDate now = LocalDate.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formatedDate = now.format(pattern);
		currentDate = formatedDate;
	}
	
	public static void initUtility() {
		System.out.println("Utility Initialized sucessfully");
	}
}
