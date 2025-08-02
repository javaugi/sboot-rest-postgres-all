/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.aop.ConcurrentOperationExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopApplicationConfig {

    @Bean
    public ConcurrentOperationExecutor concurrentOperationExecutor() {
        ConcurrentOperationExecutor executor = new ConcurrentOperationExecutor();
        executor.setMaxRetries(3);
        executor.setOrder(100);
        return executor;

    }
}
