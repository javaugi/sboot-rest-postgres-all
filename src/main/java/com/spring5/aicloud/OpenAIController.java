/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final static Logger log = LoggerFactory.getLogger(OpenAIController.class);

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping
    public Flux<String> rootEndpoint() {
        try {
            return openAIService.getData();
        } catch (JsonProcessingException e) {
            log.error("Error while processing JSON", e);
            return Flux.empty();
        }
    }
}
/*

@RestController
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }
    
    //curl "http://localhost:8080/chat?prompt=Tell me a joke"
    @GetMapping("/chat")
    public String chat(@RequestParam String prompt) {
        return openAIService.getResponse(prompt);
    }
}
// */
