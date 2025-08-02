/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.spring5.audit.TransactionalEventPublisher;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {

    @Bean
    public TransactionalEventPublisher transactionalEventPublisher(@Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus) {
        return new TransactionalEventPublisher(eventBus);
    }
}

/*
Could you Explain Spring Bean lifecycle?
Absolutely! The Spring Bean lifecycle is the process that Spring uses to manage the creation, initialization, and destruction of beans in the Spring IoC (Inversion of Control) container. Understanding this lifecycle helps you hook into various stages for custom logic (e.g., resource allocation, logging, validation, etc.).

🌀 Full Spring Bean Lifecycle Flow
🔹 1. Bean Instantiation : Spring uses the constructor (or a factory method) to create the bean instance.

    For example:

    public class MyService {
        public MyService() {
            System.out.println("Bean Constructor Called");
        }
    }
🔹 2. Populate Properties (Dependency Injection): Spring injects dependencies defined in your config (@Autowired, constructor injection, XML, etc.).

🔹 3. Set Bean Name: If the bean implements BeanNameAware, Spring calls:
    void setBeanName(String name);

🔹 4. Set Bean Factory / ApplicationContext: If you implement:
    BeanFactoryAware → gets setBeanFactory(...)
    ApplicationContextAware → gets setApplicationContext(...)

🔹 5. Post-Process Before Initialization: Spring calls BeanPostProcessors:
    Object postProcessBeforeInitialization(Object bean, String beanName)
    This allows modification before the bean is initialized.

🔹 6. Bean Initialization: Spring checks for:
    A method annotated with @PostConstruct
    InitializingBean.afterPropertiesSet()
    A custom init method defined in config or XML (init-method="...")

🔹 7. Post-Process After Initialization: Spring calls:
    Object postProcessAfterInitialization(Object bean, String beanName)
    This is often used for creating proxies (AOP, transaction wrapping, etc.)

🔹 8. Bean Is Ready to Use: The bean is now in the application context and available for use.

🔹 9. Bean Destruction: Triggered when the application context is closed. Spring checks for:
    @PreDestroy annotated method
    DisposableBean.destroy()
    A custom destroy-method="..." from config or XML

🔁 Lifecycle Hooks Summary
    Hook Type                       Interface or Annotation
    Custom init logic               @PostConstruct / afterPropertiesSet() / init-method
    Access bean name                BeanNameAware
    Access context/factory          ApplicationContextAware / BeanFactoryAware
    Post-processing (before)        BeanPostProcessor
    Post-processing (after)         BeanPostProcessor
    Custom destroy logic            @PreDestroy / DisposableBean / destroy-method
✅ Example Bean Lifecycle Demo
@Component
public class MyBean implements InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("PostConstruct called");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean.afterPropertiesSet called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy called");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean.destroy called");
    }
}
*/