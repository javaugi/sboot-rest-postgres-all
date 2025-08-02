/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

//import dev.langchain4j.service.AiService;
import org.springframework.stereotype.Service;
    
@Service
//@AiService
public interface AiAssistant {
      String chat(String message);
}
