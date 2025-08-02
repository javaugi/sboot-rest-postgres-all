/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class SecureService {
@PreAuthorize("hasRole('ADMIN')")
    public String adminOnlyMethod() {
        return "Only accessible by users with ADMIN role";
    }

    @Secured("ROLE_USER")
    public String userSecuredMethod() {
        return "Only accessible by users with USER role (via @Secured)";
    }

    @RolesAllowed("ROLE_MANAGER")
    public String managerOnlyMethod() {
        return "Only accessible by users with MANAGER role (via @RolesAllowed)";
    }

    @PreAuthorize("#username == authentication.name")
    public String accessIfOwner(String username) {
        return "Only the user themself can access this method";
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PostAuthorize("returnObject != null")
    public String modCheckAndReturn() {
        return "This will also be checked after execution";
    }    
}
