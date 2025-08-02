/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import java.util.Collections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.ControllerEntityLinks;
import org.springframework.hateoas.server.core.DefaultLinkRelationProvider;
import org.springframework.hateoas.server.core.DelegatingEntityLinks;

/**
 *
 * @author javaugi
 */
@Configuration
public class HateoasConfig {
    /*
    @Bean
    public EntityLinks entityLinks(ApplicationContext applicationContext) {
        return new DelegatingEntityLinks(
            Collections.singletonList(new ControllerEntityLinks(applicationContext))
        );
    }    
    
    @Bean
    public LinkRelationProvider linkRelationProvider() {
        return new DefaultLinkRelationProvider();
    }

    @Bean
    public EntityLinks entityLinks(LinkRelationProvider relProvider) {
        return new WebMvcEntityLinks(relProvider);
    }
    // */
}
