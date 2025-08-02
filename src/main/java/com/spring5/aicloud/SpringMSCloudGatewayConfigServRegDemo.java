/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

/**
 *
 * @author javaugi
 */
public class SpringMSCloudGatewayConfigServRegDemo {
    /*
1. Project Structure
microservices-demo/
├── api-gateway/          # Spring Cloud Gateway
├── config-server/        # Spring Cloud Config Server
├── service-registry/     # Eureka Server
├── account-service/      # Sample microservice
└── user-service/         # Sample microservice    
    
7. Running the System
Start services in this order:

Config Server (port 8888)

Service Registry (port 8761)

Account Service (default port 8081)

User Service (default port 8082)

API Gateway (port 8080)

Verify services are registered in Eureka: http://localhost:8761

Test endpoints through Gateway:

http://localhost:8080/api/accounts

http://localhost:8080/api/users

8. Key Features Demonstrated
Service Discovery: All services register with Eureka

Centralized Configuration: Config Server provides properties

API Gateway:

Route mapping (/api/accounts → account-service)

Load balancing (lb://ACCOUNT-SERVICE)

Distributed System: Services can scale independently

9. Advanced Configuration
For production, you would add:

Circuit breakers (Resilience4j)

Distributed tracing (Sleuth/Zipkin)

Security (OAuth2/JWT)

Docker/Kubernetes deployment files    
    */
    
}

/*
Java Microservices Demo with Spring Cloud Components
Here's a complete example demonstrating a microservices architecture using Spring Cloud Gateway, Config Server, and Service Registry (Eureka).

1. Project Structure
microservices-demo/
├── api-gateway/          # Spring Cloud Gateway
├── config-server/        # Spring Cloud Config Server
├── service-registry/     # Eureka Server
├── account-service/      # Sample microservice
└── user-service/         # Sample microservice
2. Service Registry (Eureka Server)
pom.xml

xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
application.yml

yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
ServiceRegistryApplication.java

java
@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}
3. Config Server
pom.xml

xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
application.yml

yaml
server:
  port: 8888

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-repo/config-repo
          clone-on-start: true
ConfigServerApplication.java

java
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
4. API Gateway
pom.xml

xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
application.yml

yaml
server:
  port: 8080

spring:
  cloud:
    gateway:
      routes:
        - id: account-service
          uri: lb://ACCOUNT-SERVICE
          predicates:
            - Path=/api/accounts/**
        - id: user-service
          uri: lb://USER-SERVICE
          predicates:
            - Path=/api/users/**
      discovery:
        locator:
          enabled: true

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
ApiGatewayApplication.java

java
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
5. Account Service (Sample Microservice)
pom.xml

xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
bootstrap.yml

yaml
spring:
  application:
    name: account-service
  cloud:
    config:
      uri: http://localhost:8888
AccountController.java

java
@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @GetMapping
    public List<Account> getAccounts() {
        return List.of(
            new Account(1, "Savings", 1000.00),
            new Account(2, "Checking", 500.00)
        );
    }
    
    @GetMapping("/{id}")
    public Account getAccount(@PathVariable int id) {
        return new Account(id, "Savings", 1000.00);
    }
}

record Account(int id, String type, double balance) {}
6. User Service (Sample Microservice)
Similar to Account Service but with:

bootstrap.yml

yaml
spring:
  application:
    name: user-service
  cloud:
    config:
      uri: http://localhost:8888
UserController.java

java
@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping
    public List<User> getUsers() {
        return List.of(
            new User(1, "John Doe", "john@example.com"),
            new User(2, "Jane Smith", "jane@example.com")
        );
    }
}

record User(int id, String name, String email) {}
7. Running the System
Start services in this order:

Config Server (port 8888)

Service Registry (port 8761)

Account Service (default port 8081)

User Service (default port 8082)

API Gateway (port 8080)

Verify services are registered in Eureka: http://localhost:8761

Test endpoints through Gateway:

http://localhost:8080/api/accounts

http://localhost:8080/api/users

8. Key Features Demonstrated
Service Discovery: All services register with Eureka

Centralized Configuration: Config Server provides properties

API Gateway:

Route mapping (/api/accounts → account-service)

Load balancing (lb://ACCOUNT-SERVICE)

Distributed System: Services can scale independently

9. Advanced Configuration
For production, you would add:

Circuit breakers (Resilience4j)

Distributed tracing (Sleuth/Zipkin)

Security (OAuth2/JWT)

Docker/Kubernetes deployment files
*/