package de.kisner.test.hibernate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.kisner.test.hibernate.model.SecurityMenu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class TestHibernate
{
	final static Logger logger = LoggerFactory.getLogger(TestHibernate.class);
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public TestHibernate()
	{
		
	}
	
	public void init()
	{
		logger.info("Init");
		emf = Persistence.createEntityManagerFactory("eap");
		em = emf.createEntityManager();
	}
	
	public void find() throws InterruptedException
	{
//		TimeUnit.SECONDS.sleep(2);
//		logger.info("Finding 1");
//		logger.info(em.find(SecurityMenu.class,1l).toString());
		
		TimeUnit.SECONDS.sleep(2);
		logger.info("Finding 2");
		logger.info(em.find(SecurityMenu.class,2l).toString());
	}
	
	public void list()
	{
		CriteriaBuilder cB = em.getCriteriaBuilder();
		CriteriaQuery<SecurityMenu> cQ = cB.createQuery(SecurityMenu.class);
		Root<SecurityMenu> from = cQ.from(SecurityMenu.class);
		CriteriaQuery<SecurityMenu> select = cQ.select(from);
		TypedQuery<SecurityMenu> tQ = em.createQuery(select);
	
		List<SecurityMenu> list = tQ.getResultList();
		logger.info(SecurityMenu.class.getSimpleName()+": "+list.size());
		
		for(SecurityMenu m : list)
		{
			m = em.find(SecurityMenu.class,m.getId());
			logger.info(m.toString());
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		TestHibernate cli = new TestHibernate();
		cli.init();
		cli.find();
//		cli.list();
	}
}