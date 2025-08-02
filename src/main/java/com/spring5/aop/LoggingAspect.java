/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.spring5.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Before " + methodName + " execution.");
    }

    @After("execution(* com.spring5.service.*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("After " + methodName + " execution.");
    }
}

/*
Spring AOP (Aspect-Oriented Programming) provides a way to separate cross-cutting concerns like logging, security, and transaction management from the core business logic of your application. By using AOP, you can avoid code duplication and make your code more modular and maintainable. 
Here's a breakdown of key concepts and how to implement AOP in Spring:
Key Concepts:
Aspect: A modular unit that encapsulates a cross-cutting concern. 
Join Point: A potential location in your application where advice can be applied (e.g., method execution, field access). 
Advice: The code that gets executed when a join point is matched. 
Pointcut: A selection criteria that determines where advice should be applied (e.g., a method in a specific package). 
Target Object: The original object being advised. 
AOP Proxy: A proxy object created by Spring AOP that intercepts method calls and applies advice. 
How to Implement AOP in Spring:
1. Define the Aspect:
Create a class that will contain the advice. 
Use the @Aspect annotation to declare the class as an aspect. 
Use the @Component annotation to make the aspect a Spring-managed bean. 
2. Define the Advice:
Use annotations like @Before, @After, @Around, @AfterReturning, and @AfterThrowing to specify where the advice should be executed. 
For example, @Before("execution(* com.example.service.AccountService.*(..))") specifies that the advice should run before any method execution in the AccountService class. 
3. Implement the Advice Logic:
Write the code that will be executed when the advice is triggered. 
For example, you might log a message, manage a transaction, or perform a security check. 
4. Enable AOP in Spring:
Enable AOP using @EnableAspectJAutoProxy annotation in your Spring configuration class. 
This annotation instructs Spring to automatically create proxies for beans that have aspects associated with them. 

*/