/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.spring5.aicloud.data.CompletionRequest;
import com.spring5.aicloud.data.EventData;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class OpenAIService {

    private final static Logger log = LoggerFactory.getLogger(OpenAIService.class);

    private final String prompt = """
                Give me a good French recipe for tonight's dinner.
                """;

    @Value("${spring.ai.openai.uri}")
    private String openAiUrl;

    @Value("${spring.ai.openai.api-key}")
    private String openAiKey;

    private WebClient client;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @PostConstruct
    public void init() {
        client = WebClient.builder()
                .baseUrl(openAiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("api-key", openAiKey)
                .build();
    }

    public Flux<String> getData() throws JsonProcessingException {
        CompletionRequest request = new CompletionRequest();
        request.setPrompt(prompt);
        request.setMaxTokens(2048);
        request.setTemperature(1.0);
        request.setFrequencyPenalty(0.0);
        request.setPresencePenalty(0.0);
        request.setTopP(0.5);
        request.setBestOf(1);
        request.setStream(true);
        request.setStop(null);

        String requestValue = objectMapper.writeValueAsString(request);
        return client.post()
                .bodyValue(requestValue)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .mapNotNull(event -> {
                    try {
                        String jsonData = event.substring(event.indexOf("{"), event.lastIndexOf("}") + 1);
                        return objectMapper.readValue(jsonData, EventData.class);
                    } catch (JsonProcessingException | StringIndexOutOfBoundsException e) {
                        return null;
                    }
                })
                .skipUntil(event -> !event.getChoices().get(0).getText().equals("\n"))
                .map(event -> event.getChoices().get(0).getText());
    }
}
