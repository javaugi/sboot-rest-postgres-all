/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/springai")
public class SpringOpenAIController {

    private final SpringOpenAIService springOpenAIService;

    public SpringOpenAIController(SpringOpenAIService springOpenAIService) {
        this.springOpenAIService = springOpenAIService;
    }

    //http://localhost:8080/openaichat?prompt=What is for dinner today
    @GetMapping    
    public ResponseEntity<String> sendMessage(@RequestParam String prompt) {
        return ResponseEntity.ok(springOpenAIService.ask(prompt));
    }
}
