/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import com.spring5.AiConfig;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepSeekService {

    private static final Logger log = LoggerFactory.getLogger(DeepSeekService.class);
    
    @Autowired
    private @Qualifier(AiConfig.REST_TEMPLATE)RestTemplate restTemplate;

    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;
    @Value("${spring.ai.deepseek.openai.base-url}")
    private String dsBaseUrl;
    @Value("${spring.ai.deepseek.openai.api-key}")
    private String dsApiKey;
    @Value("${spring.ai.deepseek.openai.chat.options.model}")
    private String dsModelDefault;

    public String getChatResponse(String userPrompt) {
        log.info("getChatResponse userPrompt {}", userPrompt);
        try {
            // 1. Prepare Headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", dsApiKey); // Replace with your API key
            headers.setBearerAuth(dsApiKey); 

            // 2. Build Request Body
            DeepSeekRequest request = new DeepSeekRequest(dsModelDefault, // Model name
                    List.of(new DeepSeekRequest.Message("user", userPrompt)), // Messages
                    0.7, /* Temperature */ 512 /* Max tokens */
            );
            log.info("getChatResponse done request {}", request);

            // 3. Send HTTP Request
            HttpEntity<DeepSeekRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
                    dsBaseUrl, HttpMethod.POST, entity, DeepSeekResponse.class
            );
            log.info("getChatResponse done response {}", response);

            // 4. Extract AI Response
            return response.getBody().getChoices().get(0).getMessage().getContent();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String getAIResponse(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", dsApiKey); // If API key is needed
        headers.setBearerAuth(dsApiKey); 
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"model\":\"deepseek-chat\",\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(dsBaseUrl, request, String.class);

        return response.getBody();
    }
}
