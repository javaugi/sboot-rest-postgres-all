/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import java.util.Arrays;


/*
Here's an example illustrating the integration of Spring Boot and Spring Cloud for building microservices:
A microservices architecture often uses Spring Cloud components for service discovery, configuration management, and API gateways. 
    This example demonstrates a simple system with a discovery service (using Eureka), a configuration server, and a client service.

Operation
    Start Eureka Server: Run the DiscoveryServiceApplication.
    Start Config Server: Run the ConfigServerApplication. Ensure a Git repository exists at the specified URI with configuration files.
    Start Client Service: Run the ClientServiceApplication. It registers with Eureka and retrieves configurations from the Config Server.
This setup allows the client service to dynamically discover and communicate with other services, centralizing configuration management
    and enhancing the resilience of the microservices architecture.
*/


//Example https://github.com/oktadev/auth0-java-microservices-examples
/*
There are two directories in this repository that pertain to this tutorial:
    spring-boot-gateway-webflux: a Spring Boot microservice architecture with Spring Cloud Gateway and Spring WebFlux.
    spring-boot-gateway-mvc: a Spring Boot microservice architecture with Spring Cloud Gateway and Spring MVC.
Each directory contains three projects:
    discovery-service: a Netflix Eureka server used for service discovery.
    car-service: a simple Car Service that uses Spring Data REST to serve up a REST API of cars.
    api-gateway: an API gateway with a /cool-cars endpoint that talks to the car service and filters out cars that aren't cool (in my opinion, of course).
The configuration for the WebFlux and MVC implementations is the same, so choose one and follow along.
*/
public class SpringCloudMicroservicePattern {
    public static void main(String[] args) {
        System.out.println("SpringCloudMicroservicePattern ... " + Arrays.toString(args));
    }
    /*
    1. Project Structure
    microservices-demo/
    ├── api-gateway/          # Spring Cloud Gateway
    ├── config-server/        # Spring Cloud Config Server
    ├── service-registry/     # Eureka Server
    ├── account-service/      # Sample microservice
    └── user-service/         # Sample microservice    
    */
    
    
    //1. Discovery Service (Eureka Server)
    //@SpringBootApplication
    //@EnableEurekaServer
    public class DiscoveryServiceApplication {
        public static void main(String[] args) {
            //SpringApplication.run(DiscoveryServiceApplication.class, args);
        }
    }    
    
    //2. Configuration Server
    //@SpringBootApplication
    //@EnableConfigServer
    public class ConfigServerApplication {
        public static void main(String[] args) {
            //SpringApplication.run(ConfigServerApplication.class, args);
        }
    }    
    
    //3. Client Service
    //@SpringBootApplication
    //@EnableDiscoveryClient
    public class ClientServiceApplication {
        public static void main(String[] args) {
            //SpringApplication.run(ClientServiceApplication.class, args);
        }
    }
    
    /*
Configuration
application.yml (Client Service).
Code

spring:
  application:
    name: client-service
  cloud:
    config:
      uri: http://localhost:8888
    discovery:
      enabled: true
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
bootstrap.yml (Config Server).
Code

spring:
  cloud:
    config:
      server:
        git:
          uri: file://${user.home}/config-repo    
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

Would you like me to elaborate on any specific part of this implementation or add any particular features?

I'm developing spring security microservice using oauth2/JWT and using one of the cloud providers aws, gcp, azure or facebook api. Could you give me details on pros and cons for each of them so that we can select a tech stack. Also could you give me java code examples for each of them or just one of them
OAuth2/JWT with Spring Security: Cloud Provider Comparison & Implementation
Cloud Provider Comparison for OAuth2/JWT
Provider	Pros	Cons
AWS Cognito	- Native AWS integration
- Free tier available
- Supports SAML & OIDC	- Steeper learning curve
- Limited customization
- AWS lock-in
Google Cloud	- Simple setup
- Excellent documentation
- Tight GCP integration	- Limited to Google ecosystem
- Fewer social identity providers
Azure AD	- Enterprise-ready
- Microsoft ecosystem integration
- B2C support	- Complex pricing
- Steeper learning curve for non-MS shops
Facebook	- Easy social login
- Simple implementation
- Free for basic use	- Limited to Facebook identities
- Privacy concerns
- Less enterprise-friendly
Recommended Stack Selection Criteria
For AWS environments: Cognito (seamless integration)

For Google ecosystems: Google Identity Platform

For Microsoft shops: Azure AD B2C

For social-heavy apps: Facebook + Google combo

For multi-cloud: Consider Auth0 or Okta instead

Spring Security + AWS Cognito Implementation Example
Here's a complete implementation using AWS Cognito as the OAuth2 provider:

1. Add Dependencies (pom.xml)
xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
2. Configure AWS Cognito (application.yml)
yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://cognito-idp.{region}.amazonaws.com/{userPoolId}
          jwk-set-uri: https://cognito-idp.{region}.amazonaws.com/{userPoolId}/.well-known/jwks.json
aws:
  cognito:
    userPoolId: us-east-1_XXXXXXXXX
    clientId: XXXXXXXXXXXXXXX
    logoutUrl: https://your-domain.auth.us-east-1.amazoncognito.com/logout
3. Security Configuration
java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${aws.cognito.logoutUrl}")
    private String logoutUrl;
    
    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .decoder(jwtDecoder())
                )
            )
            .logout(logout -> logout
                .logoutSuccessHandler(logoutSuccessHandler())
            );
        
        return http.build();
    }

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
}
4. JWT Validation Configuration
java
@Configuration
public class JwtConfig {
    
    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;
    
    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("cognito:groups");
        converter.setAuthorityPrefix("ROLE_");
        
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }
}
5. Controller Example
java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public/hello")
    public String publicEndpoint() {
        return "Hello Public!";
    }

    @GetMapping("/private/user")
    public String privateEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return String.format("Hello, %s!", jwt.getClaimAsString("email"));
    }

    @GetMapping("/admin/status")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Admin Area";
    }
}
Alternative: Google OAuth2 Implementation
Configuration Changes
yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: your-google-client-id
            client-secret: your-google-client-secret
            scope: openid,profile,email
Security Configuration
java
@EnableWebSecurity
public class GoogleSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(oidcUserService())
                )
            );
        return http.build();
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return request -> {
            OidcUser user = new DefaultOidcUser(
                AuthorityUtils.createAuthorityList("ROLE_USER"),
                request.getIdToken()
            );
            return user;
        };
    }
}
Key Considerations
Token Validation:

AWS Cognito uses JWKS endpoint

Google uses OIDC discovery endpoint

Azure AD uses Microsoft identity platform endpoints

User Attributes:

Cognito: Custom attributes via user pools

Google: Standard OIDC claims

Azure AD: Microsoft Graph API integration

Session Management:

All providers support RP-initiated logout

Session timeout handling varies

Mobile Support:

All providers have SDKs for Android/iOS

Cognito has Amplify libraries for easier integration

Would you like me to provide the implementation for any specific provider in more detail or cover additional scenarios like role-based access control with these providers?

The implementation for heavy social integration so please give java code implementation details for Facebook + Google combo
Spring Security with Facebook + Google OAuth2 Social Login
Here's a comprehensive implementation for a Spring Boot microservice supporting both Facebook and Google social logins with JWT token generation.

1. Dependencies (pom.xml)
xml
<dependencies>
    <!-- Spring Security OAuth2 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    
    <!-- JWT Support -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Web Support -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
2. Application Configuration (application.yml)
yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope: openid,profile,email
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
          facebook:
            client-id: ${FACEBOOK_CLIENT_ID}
            client-secret: ${FACEBOOK_CLIENT_SECRET}
            scope: public_profile,email
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
        provider:
          google:
            issuer-uri: https://accounts.google.com
          facebook:
            token-uri: https://graph.facebook.com/v12.0/oauth/access_token
            authorization-uri: https://www.facebook.com/v12.0/dialog/oauth
            user-info-uri: https://graph.facebook.com/v12.0/me?fields=id,name,email

app:
  jwt:
    secret: ${JWT_SECRET}
    expiration-ms: 86400000 # 24 hours
3. Security Configuration
java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**", "/login/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(oauth2AuthenticationSuccessHandler())
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService())
                )
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/").permitAll()
            );
        
        return http.build();
    }

    @Bean
    public OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler();
    }

    @Bean
    public CustomOAuth2UserService customOAuth2UserService() {
        return new CustomOAuth2UserService();
    }
}
4. Custom OAuth2 User Service
java
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        
        // Process attributes based on provider
        UserPrincipal userPrincipal = UserPrincipal.create(provider, attributes);
        
        return userPrincipal;
    }
}
5. User Principal Implementation
java
public class UserPrincipal implements OAuth2User {

    private String id;
    private String email;
    private String name;
    private String provider;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static UserPrincipal create(String provider, Map<String, Object> attributes) {
        switch (provider.toLowerCase()) {
            case "google":
                return createGoogleUser(attributes);
            case "facebook":
                return createFacebookUser(attributes);
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }

    private static UserPrincipal createGoogleUser(Map<String, Object> attributes) {
        return new UserPrincipal(
            (String) attributes.get("sub"),
            (String) attributes.get("email"),
            (String) attributes.get("name"),
            "google",
            AuthorityUtils.createAuthorityList("ROLE_USER"),
            attributes
        );
    }

    private static UserPrincipal createFacebookUser(Map<String, Object> attributes) {
        return new UserPrincipal(
            (String) attributes.get("id"),
            (String) attributes.get("email"),
            (String) attributes.get("name"),
            "facebook",
            AuthorityUtils.createAuthorityList("ROLE_USER"),
            attributes
        );
    }

    // Constructor, getters, and other methods
    // ...
}
6. JWT Token Provider
java
@Component
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-ms}")
    private int jwtExpirationMs;

    public String generateToken(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userPrincipal.getId());
        claims.put("email", userPrincipal.getEmail());
        claims.put("name", userPrincipal.getName());
        claims.put("provider", userPrincipal.getProvider());

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userPrincipal.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    // Add methods for token validation if needed
}
7. Authentication Success Handler
java
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
                                      HttpServletResponse response, 
                                      Authentication authentication) throws IOException {
        
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String token = tokenProvider.generateToken(userPrincipal);
        
        String redirectUrl = determineTargetUrl(request, response, authentication);
        redirectUrl = UriComponentsBuilder.fromUriString(redirectUrl)
            .queryParam("token", token)
            .build().toUriString();
        
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request,
                                      HttpServletResponse response, 
                                      Authentication authentication) {
        
        Optional<String> redirectUri = CookieUtils.getCookie(request, "redirect_uri")
            .map(Cookie::getValue);
        
        return redirectUri.orElse(getDefaultTargetUrl());
    }
}
8. API Endpoints
java
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", userPrincipal.getId());
        response.put("email", userPrincipal.getEmail());
        response.put("name", userPrincipal.getName());
        response.put("provider", userPrincipal.getProvider());
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout-success")
    public ResponseEntity<?> logoutSuccess() {
        return ResponseEntity.ok("Logged out successfully");
    }
}
9. Frontend Integration Example
For your frontend application, you would typically:

Initiate login by redirecting to:

Google: /oauth2/authorization/google

Facebook: /oauth2/authorization/facebook

After successful authentication, the user will be redirected back with a JWT token

Store the JWT token and use it for subsequent authenticated requests

Key Features Implemented
Multi-provider Support: Seamless integration with both Facebook and Google

JWT Generation: Secure token generation after OAuth2 success

User Principal: Unified user representation across providers

Custom Attributes: Handles different attribute structures from providers

Secure Redirects: Proper OAuth2 redirect URI handling

Setup Instructions
Create developer accounts:

Google Cloud Console

Facebook Developers

Configure OAuth2 credentials:

For Google: Enable "Google+ API" and configure consent screen

For Facebook: Create an app and add "Facebook Login" product

Set environment variables:

GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_secret
FACEBOOK_CLIENT_ID=your_facebook_app_id
FACEBOOK_CLIENT_SECRET=your_facebook_app_secret
JWT_SECRET=your_secure_jwt_secret
This implementation provides a solid foundation for social login with JWT authentication in a Spring Boot microservice. You can extend it by adding more providers (Twitter, GitHub, etc.) following the same pattern.
*/