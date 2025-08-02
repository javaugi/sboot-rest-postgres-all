/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import com.spring5.AiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OllamaService {
    //Option 1: Run DeepSeek Locally with Ollama (Recommended for Free)
    private final String OLLAMA_API = "http://localhost:11434/api/generate";
    
    @Autowired
    private @Qualifier(AiConfig.REST_TEMPLATE)RestTemplate restTemplate;

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
    
    // Call a locally running DeepSeek model (if supported)
    public String queryLocalDeepSeekModel(String prompt) {
        String requestBody = "{\"model\":\"deepseek-llm\", \"prompt\":\"" + prompt + "\"}";
        return restTemplate.postForObject(OLLAMA_API, requestBody, String.class);
    }    
}

/*
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
*/