/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mongodb;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordCountController {

    @Autowired
    private WordCountService wordCountService;

    @GetMapping("/wordcount/{filePath}")
    public Map<String, Long> getWordCount(@PathVariable String filePath) {
        return wordCountService.countWords(filePath);
    }
}