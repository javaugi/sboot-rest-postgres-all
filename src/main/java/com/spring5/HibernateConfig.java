/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import static com.spring5.MyApplication.PACKAGES_TO_SCAN;
import com.spring5.utils.MapToJsonConverter;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.cache.annotation.EnableCaching;
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
 * @author javaugi
 */
@Configuration
@EnableCaching
@EnableTransactionManagement
//@EnableEurekaServer
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@ComponentScan(basePackages = {MyApplication.PACKAGES_TO_SCAN})
@EnableJpaRepositories(basePackages = {MyApplication.PACKAGES_TO_SCAN})
public class HibernateConfig {
    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        bean.setDatabase(Database.H2);
        bean.setGenerateDdl(true);
        bean.setShowSql(true);
        return bean;
    }

    /*@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan(PACKAGES_TO_SCAN);
        return bean;
    }    
    // */
    @Bean
    public MapToJsonConverter mapToJsonConverter() {
        return new MapToJsonConverter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter, MapToJsonConverter mapToJsonConverter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan(PACKAGES_TO_SCAN);

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.attribute-converters", mapToJsonConverter);
        bean.setJpaPropertyMap(properties);

        /* DEBUUGGING
        Properties properties = new Properties();
        properties.put("javax.persistence.attribute-converters", mapToJsonConverter);
        properties.put("hibernate.session_factory.interceptor", 
        new EmptyInterceptor() {
            @Override
            public boolean onSave(Object entity, Serializable id, 
                Object[] state, String[] propertyNames, Type[] types) {
                System.out.println("entity=" + entity + "-propertyNames=" + propertyNames);
                return true;
            }
        });
        bean.setJpaProperties(properties);        
        // */
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
    
}
/*
In Hibernate/JPA, "bag" means: A @OneToMany or @ManyToMany collection without any ordering (like List, no @OrderBy).
    You are trying to fetch join two collections (probably both List<...> types) at the same time —
    Hibernate can't handle multiple fetched bags in a single query because it can't properly de-duplicate the Cartesian product.
🎯 Example causing the problem:
Suppose your entity:
    @Entity
    public class UserAccount {
        @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
        private List<Trade> trades;

        @ManyToMany(fetch = FetchType.LAZY)
        private List<Role> roles;
    }
    Now, if you do a JPQL/HQL like:
        SELECT ua FROM UserAccount ua
        LEFT JOIN FETCH ua.trades
        LEFT JOIN FETCH ua.roles
        WHERE ua.id = :id
💥 Boom! MultipleBagFetchException — because you're fetching two bags at once.
*/
/*
1. Fetch one collection at a time (Best Simple Fix)
    Change your query to only fetch one bag, and load the other lazily later. Example:
        SELECT ua FROM UserAccount ua
        LEFT JOIN FETCH ua.trades
        WHERE ua.id = :id
    Then separately lazy load ua.getRoles() when needed.
2. Change List to Set in your entity (Best Long-term Fix)
        Hibernate treats Set differently (no bag semantics) — it can fetch multiple Sets.

        Change:
            private List<Trade> trades;
            private List<Role> roles;
        to:
            private Set<Trade> trades;
            private Set<Role> roles;
        and update getters/setters.
    ✅ Then Hibernate allows multiple fetches safely.
3. Use two separate queries (Avoid Fetch Join entirely)
    First fetch the UserAccount,
    then separately initialize trades and roles:

    UserAccount account = userAccountRepository.findById(id).orElseThrow();
    Hibernate.initialize(account.getTrades());
    Hibernate.initialize(account.getRoles());
    (You need a Hibernate Session open for this — works well with Spring's OpenSessionInView.)
4. Use @BatchSize (Hibernate Optimization)
    Instead of fetch joins, let Hibernate load collections with batch fetching:
        @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
        @BatchSize(size = 20)
        private List<Trade> trades;

        @ManyToMany(fetch = FetchType.LAZY)
        @BatchSize(size = 20)
        private List<Role> roles;
    Spring JPA will load efficiently behind the scenes without join-flooding the DB.
🔥 Quick Comparison
    Fix             Pros                            Cons
    Fetch only      one collection	Very simple	Needs extra lazy loading
    Change to Set	Clean and correct               Might affect existing code
    Separate queries Clear control                  Needs careful transaction/session management
    @BatchSize	Good performance                Slightly more Hibernate config knowledge needed
*/
