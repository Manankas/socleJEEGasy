#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository._config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * REPOSITORY CONFIG POUR JPA/HIBERNATE
 * @author Fenonantenaina
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages={"${package}.repository"})
public class JPARepositoryConfig {

//	@Autowired
//	private DataSource dataSource;
    @Autowired
    private Environment env;
	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource);
//		em.setPackagesToScan("${package}.data.entites");
//		//Hibernate vendor
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//		Properties hibeProperties = new Properties();
//		hibeProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		hibeProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		hibeProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		em.setJpaProperties(hibeProperties);
//	  
//	    return em;
//	}
//	
//   @Bean
//   public PlatformTransactionManager transactionManager(){
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
//
//      return transactionManager;
//   }
//   
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }
}
