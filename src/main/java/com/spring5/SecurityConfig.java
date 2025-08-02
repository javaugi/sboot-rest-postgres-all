/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author javaugi
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(PathRequest.toH2Console());
        //this is required to make the console screen work, otherwise the RequestBody/JSON data is displayed

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**"),
                        AntPathRequestMatcher.antMatcher("/management/**"),
                        AntPathRequestMatcher.antMatcher("/actuator/**")).hasRole("ADMIN")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/user/**"),
                        AntPathRequestMatcher.antMatcher("/profile/**")).hasRole("USER")
                .requestMatchers("/admin/**", "/management/**", "/actuator/**").hasRole("ADMIN")
                .requestMatchers("/user/**", "/profile/**").hasRole("USER")
                .requestMatchers("/api/patients/**").hasAnyRole("PATIENT", "NURSE", "PHYSICIAN", "ADMIN")
                .requestMatchers("/api/nurses/**").hasAnyRole("NURSE", "PHYSICIAN", "ADMIN")
                .requestMatchers("/api/physicians/**").hasAnyRole("PHYSICIAN", "ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                //.mvcMatchers("/user/**", "/profile/**").hasRole("USER") 
                //.regexMatchers("/api/v[0-9]+/.*", "/public/.*").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                //.ignoringRequestMatchers("/api/**")
                .formLogin(form -> form
                    .loginPage("/login") // custom login page
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout") // redirect after logout
                    .permitAll()
                )
                .cors(cors -> cors
                .configurationSource(corsConfigurationSource())
                )
                //.oauth2ResourceServer(oauth2 -> oauth2
                //    .jwt(Customizer.withDefaults())
                //)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())
                );

        return http.build();
    }

    //CSRF (Cross-Site Request Forgery):
    //CORS (Cross-Origin Resource Sharing):
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // If using in-memory for testing:
    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                .username("john")
                .password("{noop}password")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN", "USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**");
    } 
    
    /*
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleUrlLogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(
                HttpServletRequest request, 
                HttpServletResponse response, 
                Authentication authentication) throws IOException {
                
                String redirectUrl = String.format(
                    "%s?client_id=%s&logout_uri=%s",
                    logoutUrl,
                    clientId,
                    URLEncoder.encode("http://localhost:8080/login", "UTF-8")
                );
                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
            }
        };
    }    
    // */
}

/*

Migrating from WebSecurityConfigurerAdapter to the New Security Configuration Style

The WebSecurityConfigurerAdapter was deprecated in Spring Security 5.7.0 and removed in Spring Boot 3.0. Here's how to migrate to the new component-based security configuration approach.

1. Understanding the Changes
    The new approach:
        Uses SecurityFilterChain beans instead of extending WebSecurityConfigurerAdapter
        Uses WebSecurityCustomizer for web security customization
        Uses UserDetailsService beans for authentication configuration
2. Basic Migration Example
3. Complete Migration Guide
    Authentication Configuration
    Web Security Customization
    OAuth2 Resource Server Configuration
4. Advanced Configuration Examples
    Role-Based Access Control
    CSRF Configuration  
    CORS Configuration
5. Multiple Security Filter Chains
6. Method Security
    Method security remains similar but now works better with the new configuration
        @Configuration
        @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
        public class MethodSecurityConfig {
            // Method security configuration
        }
        
        See SecureService for details
7. Security Configuration Tip
    Make sure your Spring Security is correctly configured to authenticate and provide roles. Roles are typically prefixed with ROLE_ in Spring.

##################################
Important Changes to Note

Method Name Changes:
    antMatchers() → requestMatchers()
    mvcMatchers() → requestMatchers()
    regexMatchers() → requestMatchers()

Lambda DSL:
    The new configuration uses a lambda-based DSL for better readability and type safety

Multiple Configurations:
    You can now define multiple SecurityFilterChain beans with different @Order

No More AuthenticationManagerBuilder:
    Configure authentication through UserDetailsService and PasswordEncoder beans

Migration Benefits
    More Flexible: Easier to compose and combine security configurations
    More Modern: Uses contemporary Java features like lambdas
    More Modular: Clear separation of concerns
    Better for Component-Based Security: Fits better with Spring's overall component model

The new approach is more aligned with Spring's general move towards functional configuration styles and provides better flexibility 
    in configuring security for modern applications.
##################################

does the sprint boot properties spring.h2.console.path=/h2-console work for other databases?

No, the Spring Boot property spring.h2.console.path=/h2-console is specific to the H2 in-memory database and its embedded web console. 
    It will not work for other databases like MySQL, PostgreSQL, Oracle, or others.

Here's why:

H2 Console Specific: The H2 console is a feature provided by the H2 database engine itself. Spring Boot provides convenient 
    auto-configuration to enable and configure this specific console using the spring.h2.console properties.   

Different Databases, Different Tools: Other databases have their own separate management tools and consoles. For example:
MySQL: MySQL Workbench, DBeaver, command-line client.
PostgreSQL: pgAdmin, DBeaver, psql command-line client.
Oracle: SQL Developer, SQLcl.
Microsoft SQL Server: SQL Server Management Studio (SSMS), Azure Data Studio.   

No Generic Console Path: There isn't a standard, universal console path property that Spring Boot can use to automatically 
enable a web console for every possible database. Each database's tooling is independent.

What you need to do for other databases:

If you want a web-based interface to interact with other databases in your Spring Boot application, you would typically:

Use a dedicated database administration tool: As mentioned above, each database usually has its own powerful desktop application for management.
    Integrate a third-party database administration tool into your application (less common for production): There might be embeddable 
    or web-based administration tools that you could potentially integrate, but this is not a standard Spring Boot feature and would require specific dependencies and configuration for that tool.
Build your own UI: For more controlled access and specific functionalities, you could build your own web interface using Spring MVC 
    or Spring WebFlux to interact with your database through your application's services and data access layer (e.g., Spring Data JPA, Spring Data JDBC).

In summary, spring.h2.console.path is exclusively for configuring the H2 database's web console. For other databases, you'll need to use 
    their respective management tools.


Spring boot spring.h2.console.path=/h2-console does not work for spring mvc resr mapping


You're right, spring.h2.console.path=/h2-console is specifically for enabling the H2 console, not for defining Spring MVC REST mappings. These are distinct functionalities:

H2 Console (spring.h2.console.path): This property configures the path where the H2 in-memory database's web console is accessible. It's used for interacting with the database directly.
Spring MVC REST Mappings (@GetMapping, @PostMapping, etc.): These annotations define the endpoints for your web application, specifying how HTTP requests should be handled by your controllers.
Why They Don't Interfere (but are often confused):

The H2 console is a separate web application embedded within your Spring Boot application. It runs independently of your Spring MVC controllers.
The /h2-console path (or whatever you set it to) is reserved for the H2 console. Spring MVC will not attempt to map any REST endpoints to that path unless you explicitly tell it to.
How to Create Spring MVC REST Mappings:

To create REST mappings in Spring MVC, you'll use annotations within your controllers:


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // Optional: Base path for all endpoints in this controller
public class MyController {

    @GetMapping("/data")
    public String getData() {
        return "This is my data!";
    }

    @PostMapping("/process")
    public String processData(@RequestBody String input) {
        return "Processed: " + input;
    }

    @GetMapping("/items/{id}")
    public String getItem(@PathVariable Long id) {
        return "Item ID: " + id;
    }
}
Explanation:

@RestController: This annotation indicates that this class is a REST controller, and its methods should return data directly (as JSON or other formats).
@RequestMapping("/api"): This optional annotation sets a base path for all endpoints within this controller.
@GetMapping("/data"): This annotation maps the /data endpoint to the getData() method, which handles GET requests.
@PostMapping("/process"): This annotation maps the /process endpoint to the processData() method, which handles POST requests.
@RequestBody: This annotation tells Spring to extract the request body and pass it as the input parameter.
@GetMapping("/items/{id}"): This annotation maps the /items/{id} endpoint, where {id} is a path variable.
@PathVariable Long id: This annotation extracts the value of the id path variable and passes it to the getItem() method.
In summary:

Use spring.h2.console.path to configure the H2 console's path.
Use @RestController, @GetMapping, @PostMapping, and related annotations to define your Spring MVC REST endpoints.
Ensure that your rest endpoints do not conflict with the H2 console path.




Spring boot spring.h2.console.path=/h2-console does not work for spring mvc rest mapping localhost:8080/h2-console display error No mapping for GET /h2-console

o.s.web.servlet.PageNotFound             : No endpoint GET /h2-console.



The error "No mapping for GET /h2-console" means that Spring Boot isn't correctly recognizing the H2 console path configuration. Here's a breakdown of the common causes and how to fix them:

1. H2 Dependency Missing:

Ensure you have the H2 database dependency in your pom.xml (Maven) or build.gradle (Gradle).

Maven (pom.xml):

XML

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
Gradle (build.gradle):

Gradle

runtimeOnly 'com.h2database:h2'
Without this dependency, Spring Boot won't be able to initialize the H2 console.

2. Spring Boot Version Compatibility:

Older versions of Spring Boot might have slightly different ways of enabling the H2 console. While /h2-console is the standard, verify the H2 console documentation for your particular spring boot version.
3. Spring Security Interference:

If you're using Spring Security, it might be blocking access to the H2 console.
Development Profile:
For development, you can temporarily disable security for the H2 console path.
Example spring security configuration:
Java

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("dev") // Apply this configuration only in the "dev" profile
public class DevSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // Allow access to H2 console
                .anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
    }
}
Make sure to add the frame options and csrf ignoring lines.
Production Profile:
In production, you should not expose the H2 console for security reasons.
4. Incorrect spring.h2.console.path Configuration:

Double-check your application.properties or application.yml file for typos.
Make sure that you have the following line:
Properties

spring.h2.console.path=/h2-console
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
or
YAML

spring:
  h2:
    console:
      path: /h2-console
      enabled: true
  datasource:
    url: jdbc:h2:mem:testdb
The spring.h2.console.enabled=true line is also very important.
5. Restart Your Application:

After making changes to your configuration or dependencies, ensure you restart your Spring Boot application.
Troubleshooting Steps:

Verify Dependencies: Check your pom.xml or build.gradle for the H2 dependency.
Check Configuration: Ensure spring.h2.console.path and spring.h2.console.enabled are correctly set.
Inspect Logs: Look at your application's logs for any errors related to H2 or Spring Security.
Disable Security (Temporarily): If you suspect Spring Security, temporarily disable it to see if that resolves the issue.
Test with a Simple Project: Create a minimal Spring Boot project with just the H2 dependency and configuration to isolate the problem.
Verify Spring boot version: Ensure compatability.
By carefully checking these points, you should be able to get the H2 console working correctly.






does the sprint boot properties spring.h2.console.path=/h2-console work for other databases?


No, the Spring Boot property spring.h2.console.path=/h2-console is specific to the H2 in-memory database and its embedded web console. It will not work for other databases like MySQL, PostgreSQL, Oracle, or others.

Here's why:

H2 Console Specific: The H2 console is a feature provided by the H2 database engine itself. Spring Boot provides convenient auto-configuration to enable and configure this specific console using the spring.h2.console properties.   
Different Databases, Different Tools: Other databases have their own separate management tools and consoles. For example:
MySQL: MySQL Workbench, DBeaver, command-line client.
PostgreSQL: pgAdmin, DBeaver, psql command-line client.
Oracle: SQL Developer, SQLcl.
Microsoft SQL Server: SQL Server Management Studio (SSMS), Azure Data Studio.   
No Generic Console Path: There isn't a standard, universal console path property that Spring Boot can use to automatically enable a web console for every possible database. Each database's tooling is independent.
What you need to do for other databases:

If you want a web-based interface to interact with other databases in your Spring Boot application, you would typically:

Use a dedicated database administration tool: As mentioned above, each database usually has its own powerful desktop application for management.
Integrate a third-party database administration tool into your application (less common for production): There might be embeddable or web-based administration tools that you could potentially integrate, but this is not a standard Spring Boot feature and would require specific dependencies and configuration for that tool.
Build your own UI: For more controlled access and specific functionalities, you could build your own web interface using Spring MVC or Spring WebFlux to interact with your database through your application's services and data access layer (e.g., Spring Data JPA, Spring Data JDBC).
In summary, spring.h2.console.path is exclusively for configuring the H2 database's web console. For other databases, you'll need to use their respective management tools.

 */
