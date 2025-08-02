/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aicloud;

import java.util.List;
import lombok.Data;

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
