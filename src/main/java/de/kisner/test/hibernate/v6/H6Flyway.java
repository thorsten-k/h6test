package de.kisner.test.hibernate.v6;

import java.io.Serializable;
import java.util.Objects;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ejb.EJBException;

public class H6Flyway implements Serializable
{
	private static final long serialVersionUID = 1L;
	final static Logger logger = LoggerFactory.getLogger(H6Flyway.class);
	
	public static void onStartup(javax.sql.DataSource dataSource)
	{
		if(Objects.isNull(dataSource))
		{
			String error = "No datasource found to execute the db migrations!";
			logger.error(error);
			throw new EJBException(error);
		}
		else
		{
			Flyway flyway = Flyway.configure()
	        		.dataSource(dataSource)
	        		.table("iodbflyway")
	        		.locations("classpath:"+"test-hibernate/system/io/db/flyway")
	        		.sqlMigrationSeparator("-")
	        		.validateMigrationNaming(true)
	        		.baselineVersion("0.0.0.1")
	        		.baselineDescription("Baseline")
	        		.load();
			
	        MigrationInfo info = flyway.info().current();
	        if (Objects.isNull(info))
	        {
	        	logger.info("No Migration Info");
	        }
	        
	        flyway.baseline();
	        flyway.migrate();
		}
	 }
}