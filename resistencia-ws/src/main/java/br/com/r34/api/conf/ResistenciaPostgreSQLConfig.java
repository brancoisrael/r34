package br.com.r34.api.conf;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"br.com.r34.persistencia.repository" }, 
entityManagerFactoryRef = "resistenciaEntityManagerFactory", 
transactionManagerRef = "resistenciaTransactionManager")

public class ResistenciaPostgreSQLConfig {

	@Autowired
	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean resistenciaEntityManagerFactory() throws NamingException {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "br.com.r34.persistencia.vo" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public PlatformTransactionManager resistenciaTransactionManager(
			final @Qualifier("resistenciaEntityManagerFactory") EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	public DataSource dataSource() throws NamingException {
		return (DataSource) new JndiTemplate().lookup(env.getProperty("spring.datasource.jndi-name"));
		
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.r34.dialect"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.r34.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.r34.format_sql"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.r34.show_sql"));
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", env.getProperty("hibernate.r34.jdbc.batch_size"));
		hibernateProperties.setProperty("hibernate.cache.use_query_cache", "hibernate.r34.cache");
		return hibernateProperties;
	}
	
}
