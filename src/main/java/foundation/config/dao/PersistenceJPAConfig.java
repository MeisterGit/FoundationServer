package foundation.config.dao;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig{
 
	/**
	 * Configures our entity manager, telling it where to look to load classes with @Entity definitions.
	 * <p>
	 * Additionally, this hooks our JPA Vendor (Hibernate!) into the entity manager, and configures it.
	 * @return A configured LocalContainerEntityManagerFactoryBean
	 */
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(dataSource());
      em.setPackagesToScan(new String[] { "foundation.dao" });
 
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      em.setJpaProperties(additionalProperties());
 
      return em;
   }
 
   /**
    * Specifies the driver, server URL, and login credentials for our MySQL database.
    * @return A DataSource object configured for our DB.
    */
   @Bean
   public DataSource dataSource(){
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/userdb");
      dataSource.setUsername( "root" );
      dataSource.setPassword( "supersecretpassword" );
      return dataSource;
   }
 
   /**
    * Configure the Java Persistence transaction manager to oversee our EntityManagerFactory.
    * @return The configured transaction manager.
    */
   @Bean
   public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(emf);
 
      return transactionManager;
   }
 
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
 
   /**
    * Extra configuration properties used by Hibernate.
    * <p>
    * create-drop = Create database tables on startup if they're missing, drop them when the server shuts down.
    * <p>
    * org.hibernate.dialect.MySQL5Dialect = Tell Hibernate to use the MySQL language to talk to our DB.
    * @return
    */
   Properties additionalProperties() {
      Properties properties = new Properties();
      properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
      properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
      return properties;
   }
}