/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

@Aspect
public class ConcurrentOperationExecutor implements Ordered {

    private static final int DEFAULT_MAX_RETRIES = 2;

    private int maxRetries = DEFAULT_MAX_RETRIES;
    private int order = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Around("execution(* com.spring5.service.*.*(..))")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (PessimisticLockingFailureException ex) {
                lockFailureException = ex;
            }
        } while (numAttempts <= this.maxRetries);
        throw lockFailureException;
    }

    @Around("execution(* com.spring5.service.*.*(..)) && "
            + "@annotation(com.xyz.service.Idempotent)")
    public Object doConcurrentOperationIdempotent(ProceedingJoinPoint pjp) throws Throwable {
        // ...
        return pjp.proceed(pjp.getArgs());
    }
}

/*
An AOP Example
Now that you have seen how all the constituent parts work, we can put them together to do something useful.

The execution of business services can sometimes fail due to concurrency issues (for example, a deadlock loser). If the operation is retried, it is likely to succeed on the next try. For business services where it is appropriate to retry in such conditions (idempotent operations that do not need to go back to the user for conflict resolution), we want to transparently retry the operation to avoid the client seeing a PessimisticLockingFailureException. This is a requirement that clearly cuts across multiple services in the service layer and, hence, is ideal for implementing through an aspect.

Because we want to retry the operation, we need to use around advice so that we can call proceed multiple times. The following listing shows the basic aspect implementation:

Java

Kotlin

@Aspect
public class ConcurrentOperationExecutor implements Ordered {

	private static final int DEFAULT_MAX_RETRIES = 2;

	private int maxRetries = DEFAULT_MAX_RETRIES;
	private int order = 1;

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Around("com.xyz.CommonPointcuts.businessService()")
	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		int numAttempts = 0;
		PessimisticLockingFailureException lockFailureException;
		do {
			numAttempts++;
			try {
				return pjp.proceed();
			}
			catch(PessimisticLockingFailureException ex) {
				lockFailureException = ex;
			}
		} while(numAttempts <= this.maxRetries);
		throw lockFailureException;
	}
}
@Around("com.xyz.CommonPointcuts.businessService()") references the businessService named pointcut defined in Sharing Named Pointcut Definitions.

Note that the aspect implements the Ordered interface so that we can set the precedence of the aspect higher than the transaction advice (we want a fresh transaction each time we retry). The maxRetries and order properties are both configured by Spring. The main action happens in the doConcurrentOperation around advice. Notice that, for the moment, we apply the retry logic to each businessService. We try to proceed, and if we fail with a PessimisticLockingFailureException, we try again, unless we have exhausted all of our retry attempts.

The corresponding Spring configuration follows:

Java

Kotlin

Xml

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfiguration {

	@Bean
	public ConcurrentOperationExecutor concurrentOperationExecutor() {
		ConcurrentOperationExecutor executor = new ConcurrentOperationExecutor();
		executor.setMaxRetries(3);
		executor.setOrder(100);
		return executor;

	}
}
To refine the aspect so that it retries only idempotent operations, we might define the following Idempotent annotation:

Java

Kotlin

@Retention(RetentionPolicy.RUNTIME)
// marker annotation
public @interface Idempotent {
}
We can then use the annotation to annotate the implementation of service operations. The change to the aspect to retry only idempotent operations involves refining the pointcut expression so that only @Idempotent operations match, as follows:

Java

Kotlin

@Around("execution(* com.xyz..service.*.*(..)) && " +
		"@annotation(com.xyz.service.Idempotent)")
public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
	// ...
	return pjp.proceed(pjp.getArgs());
}
*/