/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.spring5;

import com.spring5.graphql.PersonRepository;
import com.spring5.utils.MapToJsonConverter;
import jakarta.persistence.EntityManagerFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@SpringBootApplication
@Configuration
@EnableCaching
//@EnableTransactionManagement
//@EnableEurekaServer
//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(basePackages = {MyApplication.PACKAGES_TO_SCAN})
//@EnableJpaRepositories(basePackages = {MyApplication.PACKAGES_TO_SCAN})
//public class MyApplication implements CommandLineRunner, ApplicationListener<ApplicationEnvironmentPreparedEvent> {
public class MyApplication {

    protected static final String PACKAGES_TO_SCAN = "com.spring5";
    private final static Logger log = LoggerFactory.getLogger(MyApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        //System.exit(SpringApplication.exit(SpringApplication.run(MyApplication.class, args)));
        //new SpringApplicationBuilder().listeners(new AppController()).sources(AppController.class).run(args);
    } 
    
    /* Run sequence
    1. commandLineRunnerMain below
    2. overriding method public void run(String... args)  below
    3. Neo4jConnectionChecker public void run(String... args)
    */


    @Autowired
    private ApplicationContext context;

    /*
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        log.info("MyApplication onApplicationEvent ...");
    }
    // */

    @Bean
    public CommandLineRunner commandLineRunnerMain(PersonRepository personRepository) {
        return args -> {
            //This part runs first and then the run method below: public void run(String... args)
            log.info("MyApplication CommandLineRunner.commandLineRunnerMain args {}", Arrays.toString(args));            
        };
    }

    /*  Docker error  Error: Could not find or load main class com.spring5.MyApplication
        app-1  | Caused by: java.lang.NoClassDefFoundError: org/springframework/boot/CommandLineRunner
    @Override
    public void run(String... args) throws Exception {
        log.info("MyApplication CommandLineRunner run {}", Arrays.toString(args));
    }
    // */

}
/*
The Spring Framework's repository pattern, particularly when implemented using Spring Data JPA, commonly uses Hibernate as its underlying Java Persistence API (JPA) provider.   

Here's a breakdown of the relationship:
    JPA (Java Persistence API): This is a specification for managing persistence and object-relational mapping (ORM) 
        in Java EE and Java SE environments. It defines a standard set of interfaces and annotations for interacting with relational databases.   
    Hibernate: This is a popular and mature implementation of the JPA specification. It handles the actual mapping of Java objects to 
        database tables and provides mechanisms for querying and managing data. Other JPA implementations exist, such as EclipseLink and Apache OpenJPA.   
    Spring Data JPA: This is a module within the Spring Framework that aims to simplify the development of JPA-based data access layers. 
        It provides an abstraction layer on top of JPA providers like Hibernate.   

How Spring Data JPA uses Hibernate (or other JPA providers):
    Abstraction: Spring Data JPA provides a repository abstraction. You define interfaces (e.g., extending JpaRepository) that declare data 
        access methods without needing to write the actual implementation.   
    Automatic Implementation: Spring Data JPA automatically generates the implementation for these repository interfaces at runtime.   
    JPA Provider Integration: To perform the actual database interactions, Spring Data JPA relies on a JPA provider configured in your 
        Spring application. Hibernate is a very common choice for this JPA provider.   
    EntityManager: Spring Data JPA uses the EntityManager (provided by the JPA provider) to interact with the persistence context and 
        perform database operations. When you use Hibernate as the JPA provider, the EntityManager is managed by Hibernate's SessionFactory.

In summary:
    Spring Data JPA is a higher-level abstraction for data access using JPA.   
    JPA is a specification.   
    Hibernate is a concrete implementation of the JPA specification.   
    When you use Spring Data JPA in a typical Spring application for relational databases, Hibernate is often configured as 
        the underlying JPA provider that does the actual work of interacting with the database based on the JPA specifications and the mappings defined.   
Therefore, while Spring Data JPA doesn't require Hibernate (you could use other JPA providers), it is a very common and well-integrated 
    combination. When you see Spring Data JPA in action, it's highly likely that Hibernate (or another JPA implementation) is working behind the scenes.
#####################################
You can determine which JPA provider Spring Data JPA is using by examining your Spring application's configuration. Here are the common ways 
    to identify the JPA provider:
1. Dependencies in your Build File (pom.xml for Maven or build.gradle for Gradle):
    Hibernate: If you see a dependency on org.hibernate:hibernate-core (and potentially org.hibernate:hibernate-entitymanager), it's highly likely that
        Hibernate is being used as the JPA provider.
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jpa</artifactId>
            </dependency>

             ```gradle
             // Gradle (build.gradle)
             implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
             // This starter usually pulls in Hibernate by default
             ```
            EclipseLink: If you intend to use EclipseLink, you would typically exclude the default Hibernate dependency and include the EclipseLink dependency:
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jpa</artifactId>
                <exclusions>
                    <exclusion>
                        <groupId>org.hibernate</groupId>
                        <artifactId>hibernate-entitymanager</artifactId>
                    </exclusion>
                </exclusions>
            </dependency>
            <dependency>
                <groupId>org.eclipse.persistence</groupId>
                <artifactId>org.eclipse.persistence.orm</artifactId>
                <version>YOUR_ECLIPSELINK_VERSION</version>
            </dependency>
            // Gradle (build.gradle)
            implementation('org.springframework.boot:spring-boot-starter-data-jpa') {
                exclude group: 'org.hibernate', module: 'hibernate-entitymanager'
            }
            implementation 'org.eclipse.persistence:org.eclipse.persistence.orm:YOUR_ECLIPSELINK_VERSION'
    Other JPA Providers: Similarly, if you were using another JPA provider like Apache OpenJPA, you would include its specific dependency and exclude Hibernate.
2. Spring Boot Auto-Configuration (for Spring Boot Applications):
    Spring Boot     automatically configures a JPA provider if it finds one on the classpath.
    Default:        By default, if you include the spring-boot-starter-data-jpa dependency without explicitly specifying 
                        a different provider, Spring Boot will auto-configure Hibernate.
    If you include the dependencies for another JPA provider (like EclipseLink) and exclude Hibernate, Spring Boot will 
        typically auto-configure that provider instead.
3. Explicit Configuration in application.properties or application.yml:
    You can explicitly specify the JPA provider using the spring.jpa.database-platform property. This property tells Spring Data JPA which database dialect to use, and it often implies the underlying JPA provider.
        Properties
        # application.properties (for Hibernate)
        spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
        Properties

        # application.properties (for EclipseLink)
        spring.jpa.database-platform=org.eclipse.persistence.platform.database.MySQLPlatform
        While spring.jpa.database-platform primarily configures the dialect, it's a strong indicator of the JPA provider in use.
4. Explicit Bean Configuration (Less Common in Spring Boot):
        In more advanced or non-Spring Boot scenarios, you might explicitly configure the EntityManagerFactory bean. The class of this bean (e.g., LocalContainerEntityManagerFactoryBean) and the JpaVendorAdapter associated with it will reveal the JPA provider.

        @Configuration
        public class JpaConfig {

            @Bean
            public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
                LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
                em.setDataSource(dataSource);
                // ... other configurations

                HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
                em.setJpaVendorAdapter(vendorAdapter); // Indicates Hibernate

                // If using EclipseLink:
                // EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
                // em.setJpaVendorAdapter(vendorAdapter);

                // ... other configurations
                return em;
            }

            // ... other beans like DataSource, TransactionManager
        }
        
In summary, to know which JPA provider Spring Data JPA is using:
    Check your project's dependencies. The presence of hibernate-core (without explicit exclusion when using 
        spring-boot-starter-data-jpa) strongly suggests Hibernate. Look for dependencies of other JPA providers 
        if you suspect a different one is being used.
    Examine your application.properties or application.yml file. The spring.jpa.database-platform property can give a strong hint.
    For Spring Boot applications, understand that Hibernate is the default if no other provider is explicitly configured.
    In more complex setups, look at the explicit bean configuration for the EntityManagerFactory and its JpaVendorAdapter.

By checking these aspects of your project's configuration, you can reliably determine which JPA provider is being used by Spring Data JPA.


 */
