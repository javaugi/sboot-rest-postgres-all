/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import com.spring5.AiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author javaugi
 */
@Service
public class SpringOpenAIService {
    @Value("${application.openai.url}")
    private String apiUrl;

    @Value("${application.openai.key}")
    private String apiKey;

    @Value("${openai.api.model}")
    private String modelVersion;

    @Autowired
    private @Qualifier(AiConfig.REST_TEMPLATE)RestTemplate restTemplate;    

    /**
     * @param prompt - the question you are expecting to ask ChatGPT
     * @return the response in JSON format
     */
    public String ask(String prompt) {
        HttpEntity<String> entity = new HttpEntity<>(buildMessageBody(modelVersion, prompt), buildOpenAIHeaders());
        
        return restTemplate
                .exchange(apiUrl, HttpMethod.POST, entity, String.class)
                .getBody();
    }

    private HttpHeaders buildOpenAIHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private String buildMessageBody(String modelVersion, String prompt) {
        return String.format("{ \"model\": \"%s\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}", modelVersion, prompt);
    }    
}
