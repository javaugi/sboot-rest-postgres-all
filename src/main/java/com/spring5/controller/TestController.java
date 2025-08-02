/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint - no auth needed";
    }

    @GetMapping("/patient")
    @PreAuthorize("hasRole('PATIENT')")
    public String patientEndpoint() {
        return "Patient endpoint";
    }

    @GetMapping("/nurse")
    @PreAuthorize("hasRole('NURSE')")
    public String nurseEndpoint() {
        return "Nurse endpoint";
    }

    @GetMapping("/physician")
    @PreAuthorize("hasRole('PHYSICIAN')")
    public String physicianEndpoint() {
        return "Physician endpoint";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Admin endpoint";
    }
}

/*
Key Features of This Implementation:

Role-Based Access Control:
    Different roles (PATIENT, NURSE, PHYSICIAN, ADMIN) have different access levels
    Method-level security with @PreAuthorize
    JWT Token Validation:
    Validates tokens from OAuth2 provider
    Extracts roles from tokens

Comprehensive Security Configuration:
    CSRF protection disabled for stateless API
    Session management set to STATELESS
    CORS configuration

Custom Validation:
    Validates roles against the enum
    Custom error handling for security exceptions

Integration with OAuth2 Providers:
    Works with Keycloak, Auth0, Okta, etc.
    Configurable via application properties
    
Deployment Considerations:
    Configure your OAuth2 provider (Keycloak, Auth0, etc.) with:
    Proper client setup
    Role definitions
    User role assignments
Ensure your JWT tokens include:
    realm_access.roles claim (for Keycloak)
    Or equivalent role claims for your provider
    Set up proper CORS policies if your frontend is separate

This implementation provides a secure foundation for your healthcare application with proper role-based access control and OAuth2 integration.
*/