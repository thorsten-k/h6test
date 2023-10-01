package de.kisner.test.hibernate.v6;

import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.jeesl.model.ejb.system.security.context.SecurityMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class H6Test
{
	final static Logger logger = LoggerFactory.getLogger(H6Test.class);
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public H6Test()
	{
		
	}
	
	public void init()
	{
		logger.info("Init");
		emf = Persistence.createEntityManagerFactory("h6test");
		em = emf.createEntityManager();
	}
	
	public void flyway()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setUsername("h6test");
		ds.setPassword("ksjNRGLOnwegbadfbre");

		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:postgresql://");
		sb.append("localhost");
		sb.append(":30016");
		sb.append("/").append("h6test");
//		sb.append("?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
		logger.info("Postgres Connection: "+sb.toString());
		
		ds.setUrl(sb.toString());
		
		H6Flyway.onStartup(ds);
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
	
	public static void main(String[] args)
	{
		H6Test cli = new H6Test();
		cli.init();
		cli.flyway();
		cli.list();
	}
}