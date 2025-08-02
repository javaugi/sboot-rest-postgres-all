/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.docker;

import com.spring5.MyApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/docker")
public class DockerController {
    private final static Logger log = LoggerFactory.getLogger(DockerController.class);
    
    @GetMapping
    public String index(final Model model) {
        model.addAttribute("title", "Docker + Spring Boot");
        model.addAttribute("msg", "Welcome to the docker container!");
        log.info("DockerController index");
        return "index";
    }   
    
    @RequestMapping("/home")
    public String home() {
        log.info("DockerController /home");
        return "Hello Docker World";
    }    
}
