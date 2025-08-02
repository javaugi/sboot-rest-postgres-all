/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.misc.servicelocator;

/**
 * The service locator design pattern is used when we want to locate various
 * services using JNDI lookup. Considering high cost of looking up JNDI for a
 * service, Service Locator pattern makes use of caching technique. For the
 * first time a service is required, Service Locator looks up in JNDI and caches
 * the service object. Further lookup or same service via Service Locator is
 * done in its cache which improves the performance of application to great
 * extent. Following are the entities of this type of design pattern.
 *
 * Service - Actual Service which will process the request. Reference of such
 * service is to be looked upon in JNDI server.
 *
 * Context / Initial Context - JNDI Context carries the reference to service
 * used for lookup purpose.
 *
 * Service Locator - Service Locator is a single point of contact to get
 * services by JNDI lookup caching the services.
 *
 * Cache - Cache to store references of services to reuse them
 *
 * Client - Client is the object that invokes the services via ServiceLocator.
 *
 *
 * At first glance, the Service Locator pattern looks the same as the Abstract
 * Factory pattern to me. They both seem to have the same use (you query them to
 * receive instances of abstract services), and they both have been mentioned
 * when I read about Dependency Injection.
 *
 * However, I have seen the Service Locator pattern described as a poor idea,
 * but have seen direct support for the Abstract Factory pattern in at least one
 * major Dependency Injection framework.
 *
 *
 * I have stumbled across the same question while investigating these patterns.
 * I think the major differences can be found between a Service Locator and a
 * Factory (whether it is abstract or not):
 *
 * Service Locator
 *
 * 'Locates' an existing dependency (the service). Although the service may be
 * created during resolution, it is of no consequence to the Client because:
 *
 * The Client of the Service Locator does NOT take ownership of the dependency.
 *
 * Explicitly supports registration of which concrete objects should be
 * created/returned
 *
 *
 * Usually has a generic interface, allowing the user to request any abstract
 * type, rather than specific types
 *
 *
 * May itself be concrete
 *
 *
 * Factory
 *
 * Creates a new instance of a dependency.
 *
 * The Client of the Factory takes ownership of the dependency.
 *
 * Abstract Factory
 *
 * Same as a regular Factory except that different deployments may use different
 * implementations of the Abstract Factory allowing different types to be
 * instantiated in those different deployments (you could even change the
 * implementation of the Abstract Factory at runtime but that's not usually how
 * it's used.)
 *
 * May not support registration - that is up to the specific implementation to
 * support, or not support, and probably wouldn't be exposed on the abstract
 * interface
 *
 * Usually has multiple get methods for specific abstract types
 *
 * Is not itself concrete (though will of course have concrete implementations)
 *
 * Some severe contradictions arises:
 *
 * Seemann said: "An Abstract Factory is a generic type, and the return type of
 * the Create method is determined by the type of the factory itself. In other
 * words, a constructed type can only return instances of a single type."
 *
 * While Rober C. Martin didn't mention anything about generic types and
 * furthermore, factory example in his book allow to create instance of more
 * than one type of objects distinguish between them using a key string as
 * parameter in the Factory.Make().
 *
 * Gamma said that intent of Abstract Factory is to "Provide an interface for
 * creating families of related or dependent objects without specifying their
 * concrete classes". Is worth to mention that Gamma Abstract Factory example
 * violate Interface Segregation Principle (ISP) stated by Martin. ISP and SOLID
 * in general are more moderns principles or maybe for simplicity where omitted.
 *
 * Gamma and Martin's works precede Seemann's, so I think he should follow
 * definition already made.
 *
 * While Fowler propose Service Locator as a way to implement Dependency
 * Inversion, Seemann consider it as an anti-pattern. Neither Gamma or Martin
 * mention Service Locator.
 *
 * However, Seemann and Fowler agreed in that Service Locator needs a
 * configuration step to register an instance of a concretes class, that
 * instance is what will be later returned when an object of that kind be
 * requested. This configuration step is not mentioned by Martin or Gamma in
 * their definition of Abstract Factory. Abstract Factory pattern suppose a new
 * object to be instantiated every time an object of that kind be requested.
 *
 * Conclusion
 *
 * The main difference between Service Locator and Abstract Factory is that
 * Abstract Factory suppose a new object be instantiated an returned at each
 * requested and Service Locator needs to be configured with an object instance
 * and every time the same instance will be returned.
 *
 *
 * An Abstract Factory is a generic type, and the return type of the Create
 * method is determined by the type of the factory itself. In other words, a
 * constructed type can only return instances of a single type.
 *
 * A Service Locator, on the other hand, is a non-generic interface with a
 * generic method. The Create method of a single Service Locator can return
 * instances of an infinite number of types.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ServiceLocatorPattern {

    public static void main(String[] args) {
        Service service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
        service = ServiceLocator.getService("Service1");
        service.execute();
        service = ServiceLocator.getService("Service2");
        service.execute();
    }
}
