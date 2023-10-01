package de.kisner.h6test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class H6Test
{
	final static Logger logger = LoggerFactory.getLogger(H6Test.class);
	
	public H6Test()
	{
		
	}
	
	public void init()
	{
		logger.info("Init");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h6test");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	public static void main(String[] args)
	{
		H6Test test = new H6Test();
		test.init();
		
	}
}