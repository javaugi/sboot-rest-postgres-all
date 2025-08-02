/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResilienceDemoController {

    private final ResilientService resilientService;
    private final Resilience4jExternalService externalService; // To see the raw service calls

    public ResilienceDemoController(ResilientService resilientService, Resilience4jExternalService externalService) {
        this.resilientService = resilientService;
        this.externalService = externalService;
    }

    @GetMapping("/resilient-call")
    public String makeResilientCall() {
        return resilientService.performResilientOperation();
    }

    @GetMapping("/external-stats")
    public String getExternalServiceStats() {
        return "External Service Stats: Successes = " + externalService.getSuccessCount() + ", Failures = " + externalService.getFailureCount();
    }
}