/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.spring5.EventBusConfig;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.subscription.SubscriptionContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlingInterceptor {//implements IHandlerInterceptor {

    private final MBassador<Object> eventBus;
    private final RetryTemplate retryTemplate;

    public ErrorHandlingInterceptor(@Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus) {
        this.eventBus = eventBus;
        this.retryTemplate = new RetryTemplate();

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000); // 1 second

        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
    }

    //@Override
    public Object onAfterHandling(Object message,
                                  SubscriptionContext context,
                                  Object handler,
                                  Throwable exception) {
        if (exception != null) {
            retryTemplate.execute(retryContext -> {
                eventBus.publish(message); // Retry publishing
                return null;
            });
        }
        return null; // Interceptor contract
    }
}

/*
import net.engio.mbassy.bus.MBassador;
//import net.engio.mbassy.bus.publication.;
import net.engio.mbassy.listener.IHandlerInterceptor;
import net.engio.mbassy.subscription.Subscription;
//import org.springframework.boot.autoconfigure.pulsar.PulsarProperties.Consumer.Subscription;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlingInterceptor implements IHandlerInterceptor {

    private final MBassador<Object> eventBus;
    private final RetryTemplate retryTemplate;

    public ErrorHandlingInterceptor(MBassador<Object> eventBus) {
        this.eventBus = eventBus;
        this.retryTemplate = new RetryTemplate();
        
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);
        
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(1000); // 1 second
        
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
    }

    @Override
    public void onAfterHandling(Object message, Subscription subscription, Object listener, Throwable exception) {
        if (exception != null) {
            retryTemplate.execute(context -> {
                eventBus.publish(message); // Retry publishing
                return null;
            });
        }
    }
}
// */