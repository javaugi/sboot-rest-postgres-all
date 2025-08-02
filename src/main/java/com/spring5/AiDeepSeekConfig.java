/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;

//https://platform.deepseek.com/transactions
//https://platform.deepseek.com/api_keys
@Configuration
public class AiDeepSeekConfig implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(AiDeepSeekConfig.class);
    
    @Value("classpath:/prompts/system-message.st")
    private Resource systemResource;    
    @Value("${spring.ai.deepseek.openai.base-url}")
    private String baseUrl;
    @Value("${spring.ai.deepseek.openai.api-key}")
    private String apiKey;
    @Value("${spring.ai.deepseek.openai.chat.options.model}")
    private String apiModel;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("AiDeepSeekConfig with baseUrl {} args {} ", baseUrl, Arrays.toString(args)); 
        hide(systemResource, apiKey, apiModel);
    }

    private void hide(Resource resource, String... args) {
        //doNothing
    }
    

    @Primary
    @Bean(name = "deekseekOpenAiApi")
    public OpenAiApi chatCompletionApi() {
        return OpenAiApi.builder().baseUrl(baseUrl).apiKey(apiKey).build();
    }

    @Primary
    @Bean(name = "deekseekOpenAiChatModel")
    public OpenAiChatModel openAiClient(OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(OpenAiChatOptions.builder().model(apiModel).build())
                .build();
    }       
}
/* test
curl https://api.deepseek.com/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <DeepSeek API Key>" \
  -d '{
        "model": "deepseek-chat",
        "messages": [
          {"role": "system", "content": "You are a helpful assistant."},
          {"role": "user", "content": "Hello!"}
        ],
        "stream": false
      }'
*/

/*
Do I need to pay to run spring boot ai using deepseek
No, you don't need to pay to run Spring Boot AI using DeepSeek if you're using the free tier of DeepSeek's API.

Here’s how you can integrate DeepSeek AI with Spring Boot for free:
Get DeepSeek API Key (if required):

Check if DeepSeek provides a free API tier (like OpenAI’s free tier).

Some AI models may require an API key, but if DeepSeek has an open-access model (like their local models), you might not need one.

Spring Boot Integration (for calling DeepSeek AI):

Use RestTemplate or WebClient to call DeepSeek’s API (if they offer a REST endpoint).

Example:

java
@Service
public class DeepSeekAIService {
    private final String DEEPSEEK_API_URL = "https://api.deepseek.com/v1/chat/completions"; // Example URL
    
    public String getAIResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer YOUR_DEEPSEEK_API_KEY"); // If API key is needed
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String requestBody = "{\"model\":\"deepseek-chat\",\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]}";
        
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(DEEPSEEK_API_URL, request, String.class);
        
        return response.getBody();
    }
}
Running Locally (Free Option):

If DeepSeek provides open-weight models (like Llama, Mistral), you can run them locally with Spring Boot + Ollama or Transformers.

Example using Ollama (local LLM runner):

java
// Call a locally running DeepSeek model (if supported)
public String queryLocalDeepSeekModel(String prompt) {
    RestTemplate restTemplate = new RestTemplate();
    String OLLAMA_URL = "http://localhost:11434/api/generate";
    String requestBody = "{\"model\":\"deepseek-llm\", \"prompt\":\"" + prompt + "\"}";
    return restTemplate.postForObject(OLLAMA_URL, requestBody, String.class);
}
Do You Need to Pay?
✅ Free if:

Using DeepSeek’s free API tier (if available).

Running locally (if DeepSeek provides downloadable models).

❌ Paid if:

You exceed DeepSeek’s free API limits.

You use a pro/premium model version.

Best Free Approach?
If DeepSeek allows local model execution, download it and run it via:

Ollama (for LLMs)

Spring Boot + Python (via ProcessBuilder) if the model is Python-based.

Would you like help setting up a local AI model with Spring Boot
*/

/*
Great! Let’s set up a Spring Boot application integrated with a locally run AI model (like DeepSeek, Llama, or Mistral) so you can avoid paying for API calls. Here’s a step-by-step guide:

Option 1: Run DeepSeek Locally with Ollama (Recommended for Free)
Ollama lets you run open-source LLMs (like DeepSeek, Llama 3, Mistral) locally. We’ll call it from Spring Boot.

Step 1: Install Ollama
Download & Install

Ollama Official Site (macOS/Linux/WSL)

For Windows (WSL):

bash
wget https://ollama.ai/download/OllamaSetup.exe
Pull DeepSeek Model (or any open LLM)

bash
ollama pull deepseek-llm  # Replace with available model if needed
(If DeepSeek isn’t available, try ollama pull llama3 or mistral.)

Run the Model

bash
ollama run deepseek-llm
(Test with a prompt to confirm it works.)

Step 2: Spring Boot Setup
Create a Spring Boot Project

Use Spring Initializr with:

Spring Web

Lombok (optional)

Call Ollama API from Spring Boot
Ollama runs a local REST API at http://localhost:11434.

Service Class (OllamaService.java)

java
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {
    
    private final String OLLAMA_API = "http://localhost:11434/api/generate";
    private final RestTemplate restTemplate = new RestTemplate();

    public String askAI(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = """
            {
                "model": "deepseek-llm",
                "prompt": "%s",
                "stream": false
            }
            """.formatted(prompt);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(
            OLLAMA_API, 
            request, 
            String.class
        );

        return response.getBody();
    }
}
Create a REST Controller (AiController.java)

java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final OllamaService ollamaService;

    public AiController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String prompt) {
        return ollamaService.askAI(prompt);
    }
}
Test It!
Run Spring Boot (mvn spring-boot:run) and send a POST request:

bash
curl -X POST http://localhost:8080/api/ai/ask -d "What is Spring Boot?"
You’ll get a response from your local AI model!

Option 2: Use Transformers (Python + Spring Boot)
If you prefer Python-based models (like Hugging Face’s transformers), you can run a Python AI server and call it from Spring Boot.

Step 1: Set Up Python AI Server
Install Python dependencies:

bash
pip install flask transformers torch
Create ai_server.py:

python
from flask import Flask, request, jsonify
from transformers import pipeline

app = Flask(__name__)
model = pipeline("text-generation", model="deepseek-ai/deepseek-llm-7b")  # Example model

@app.route("/ask", methods=["POST"])
def ask():
    prompt = request.json.get("prompt")
    response = model(prompt, max_length=50)
    return jsonify({"response": response[0]["generated_text"]})

if __name__ == "__main__":
    app.run(port=5000)
Run the server:

bash
python ai_server.py
Step 2: Call Python AI from Spring Boot
Modify OllamaService to call the Python server instead:

java
public String askAI(String prompt) {
    String PYTHON_API = "http://localhost:5000/ask";
    String requestBody = "{\"prompt\":\"" + prompt + "\"}";
    // Rest of the code remains the same (just change URL)
}
Which Option Should You Choose?
Approach	Pros	Cons
Ollama (Local)	Free, Fast, No API limits	Requires local GPU (for big models)
Python Server	Works with Hugging Face models	Slower, Needs Python setup
Final Notes
✅ For a free, self-hosted AI in Spring Boot:

Use Ollama + DeepSeek/Llama/Mistral (best balance).

If DeepSeek isn’t available, llama3 or mistral are great alternatives.

🚀 Want to deploy this?

Use a cloud VM with a GPU (like AWS EC2) if running larger models.

Let me know if you’d like help optimizing this further! 😊

I'll use this url https://api.deepseek.com/v1/chat/completions could you give me Java spring boot code examples with services and rest controllers
Certainly! Below is a complete Spring Boot implementation for calling the DeepSeek API (https://api.deepseek.com/v1/chat/completions) with services, DTOs, and REST controllers.

Step 1: Add Dependencies (pom.xml)
Ensure you have spring-boot-starter-web and lombok (optional for reducing boilerplate code):

xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
Step 2: Define Request/Response DTOs
We’ll model the DeepSeek API’s expected request/response format.

DeepSeekRequest.java
java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekRequest {
    private String model;          // e.g., "deepseek-chat"
    private List<Message> messages;
    private double temperature;    // (Optional) Default: 0.7
    private int max_tokens;        // (Optional) Default: 512

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String role;      // "user" or "assistant"
        private String content;
    }
}
DeepSeekResponse.java
java
import lombok.Data;
import java.util.List;

@Data
public class DeepSeekResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;

    @Data
    public static class Choice {
        private Message message;
        private int index;
        private String finish_reason;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
    }
}
Step 3: Create a Service to Call DeepSeek API
DeepSeekService.java
java
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepSeekService {
    private static final String DEEPSEEK_API_URL = "https://api.deepseek.com/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public String getChatResponse(String userPrompt) {
        // 1. Prepare Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer YOUR_DEEPSEEK_API_KEY"); // Replace with your API key

        // 2. Build Request Body
        DeepSeekRequest request = new DeepSeekRequest(
            "deepseek-chat",  // Model name
            List.of(new DeepSeekRequest.Message("user", userPrompt)), // Messages
            0.7,  // Temperature
            512   // Max tokens
        );

        // 3. Send HTTP Request
        HttpEntity<DeepSeekRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
            DEEPSEEK_API_URL,
            HttpMethod.POST,
            entity,
            DeepSeekResponse.class
        );

        // 4. Extract AI Response
        return response.getBody().getChoices().get(0).getMessage().getContent();
    }
}
Step 4: Create a REST Controller
DeepSeekController.java
java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deepseek")
public class DeepSeekController {
    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/chat")
    public String chat(@RequestBody String userPrompt) {
        return deepSeekService.getChatResponse(userPrompt);
    }
}
Step 5: Test the API
Run Spring Boot (mvn spring-boot:run).

Send a POST request (e.g., using curl or Postman):

bash
curl -X POST http://localhost:8080/api/deepseek/chat \
     -H "Content-Type: text/plain" \
     -d "Explain quantum computing in simple terms."
Response:

json
"Quantum computing uses qubits to perform calculations much faster than classical computers..."
Key Notes
API Key: Replace YOUR_DEEPSEEK_API_KEY with your actual key (store it securely using application.properties or environment variables).

Error Handling: Add try-catch for RestTemplate exceptions (e.g., HttpClientErrorException).

Async Support: For scalability, use WebClient (reactive) instead of RestTemplate.

Would you like me to extend this with:

API key security (e.g., @Value from application.properties)?

Async (WebClient) implementation?

Swagger/OpenAPI documentation?
*/