/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

/**
 *
 * @author javaugi
 */
public class SpringSecFacebookGoogleDemo {
    
}

/*
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