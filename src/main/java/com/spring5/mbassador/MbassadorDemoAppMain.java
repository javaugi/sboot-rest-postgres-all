    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import com.spring5.EventBusConfig;
import jakarta.annotation.PostConstruct;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
/**
 *
 * @author javaugi
 */
public class MbassadorDemoAppMain {
    private static final MbassadorDemoAppMain main = new MbassadorDemoAppMain();
    
    @Autowired
    private @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;
    @Autowired
    private MBassadorOrderService orderService;
    @Autowired
    private PaymentService paymentService;
    
    public static void main(String[] args) {
        main.run();
        main.demo();
    }    
    
    
    private void run() {
        // Initialize
        NotificationService notifications = new NotificationService();
        
        // Subscribe listeners
        eventBus.subscribe(notifications);
        
        // Publish events
        eventBus.publish(new OrderCreatedEvent("123"));
        eventBus.publish(new PaymentProcessedEvent(99.99));        
    }
    
    
    @PostConstruct
    public void demo() {
        orderService.createOrder("ORD-123", "customer@example.com");
        paymentService.processPayment("ORD-123", 99.99);
    }
}
/*
Here's a detailed guide on using Mbassador (a lightweight, high-performance event bus for Java) with code examples and design patterns:

1. Mbassador Overview
    Mbassador is a pub/sub event bus that:
    Supports synchronous and asynchronous event handling
    Uses strong typing (no manual event type checking)
    Offers event filtering, priority handling, and dead event handling
    Is lightweight (no dependencies) and fast (~20M events/sec)

2. Setup & Basic Usage
Add Dependency (Maven)
    xml
    Copy
    <dependency>
        <groupId>net.engio</groupId>
        <artifactId>mbassador</artifactId>
        <version>1.3.2</version>
    </dependency>
Run HTML
Create an Event Bus

import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.bus.config.BusConfiguration;

// Create a bus with default configuration
MBassador<Object> eventBus = new MBassador<>(BusConfiguration.Default());
Define Events (POJOs)
// Simple event
public class OrderCreatedEvent {
    private String orderId;
    public OrderCreatedEvent(String orderId) { this.orderId = orderId; }
    // getter...
}

// Another event type
public class PaymentProcessedEvent {
    private double amount;
    public PaymentProcessedEvent(double amount) { this.amount = amount; }
    // getter...
}
Subscribe Listeners
public class NotificationService {

    // Register handler for OrderCreatedEvent
    @Handler
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("Notification: Order created - " + event.getOrderId());
    }

    // Register handler for PaymentProcessedEvent
    @Handler
    public void handlePayment(PaymentProcessedEvent event) {
        System.out.println("Notification: Payment processed - $" + event.getAmount());
    }
}
Publish Events
public class Main {
    public static void main(String[] args) {
        // Initialize
        MBassador<Object> bus = new MBassador<>();
        NotificationService notifications = new NotificationService();
        
        // Subscribe listeners
        bus.subscribe(notifications);
        
        // Publish events
        bus.publish(new OrderCreatedEvent("123"));
        bus.publish(new PaymentProcessedEvent(99.99));
    }
}
3. Advanced Features
A. Asynchronous Event Handling
import net.engio.mbassy.bus.config.Feature;
import net.engio.mbassy.bus.config.IBusConfiguration;

// Configure async bus
MBassador<Object> asyncBus = new MBassador<>(
    new BusConfiguration()
        .addFeature(Feature.AsynchronousMessageDispatch.Default())
);

// Handler will execute in a separate thread
@Handler(delivery = Invoke.Asynchronously)
public void asyncHandler(OrderCreatedEvent event) {
    // Time-consuming operation
}
B. Event Filtering
// Only handle events where amount > 100
@Handler(delivery = Invoke.Synchronously)
@Filter(condition = "event.amount > 100")
public void handleLargePayment(PaymentProcessedEvent event) {
    System.out.println("Large payment detected: $" + event.getAmount());
}
C. Priority Handling
// Higher priority executes first (default = 0)
@Handler(priority = 10)
public void highPriorityHandler(OrderCreatedEvent event) {
    System.out.println("High priority handler");
}

@Handler(priority = 5)
public void mediumPriorityHandler(OrderCreatedEvent event) {
    System.out.println("Medium priority handler");
}
D. Dead Event Handling
// Handle events with no subscribers
@Handler
public void handleDeadEvent(DeadEvent deadEvent) {
    System.out.println("No subscribers for: " + deadEvent.getMessage());
}
4. Design Patterns with Mbassador
Pattern 1: Event-Driven Microservices
// OrderService.java
public class OrderService {
    private MBassador<Object> bus;
    
    public void createOrder(String orderId) {
        // Business logic...
        bus.publish(new OrderCreatedEvent(orderId));
    }
}

// ShippingService.java
public class ShippingService {
    @Handler
    public void prepareShipping(OrderCreatedEvent event) {
        System.out.println("Preparing shipment for order " + event.getOrderId());
    }
}
Pattern 2: CQRS (Command Query Responsibility Segregation)
// Command: Modify state
public class UpdateInventoryCommand {
    private String productId;
    private int quantity;
    // constructor, getters...
}

// Event: State change notification
public class InventoryUpdatedEvent {
    private String productId;
    private int newQuantity;
    // constructor, getters...
}

// Command Handler
public class InventoryCommandHandler {
    @Handler
    public void handle(UpdateInventoryCommand cmd) {
        // Update database...
        bus.publish(new InventoryUpdatedEvent(cmd.getProductId(), newQuantity));
    }
}

// Read Model Updater
public class InventoryReadModel {
    @Handler
    public void onInventoryUpdate(InventoryUpdatedEvent event) {
        // Update cached view
    }
}
Pattern 3: Error Handling
public class ErrorHandler {
    @Handler
    public void handleErrors(Throwable error) {
        // Centralized error logging
        System.err.println("Error occurred: " + error.getMessage());
    }
}

// Register error handler
bus.subscribe(new ErrorHandler());
5. Performance Optimization
A. Benchmarking
// Test event throughput
long start = System.currentTimeMillis();
for (int i = 0; i < 1_000_000; i++) {
    bus.publish(new OrderCreatedEvent("order-" + i));
}
long duration = System.currentTimeMillis() - start;
System.out.println("Events/sec: " + (1_000_000 / (duration / 1000.0)));
B. Configuration Tweaks
// High-performance configuration
MBassador<Object> highPerfBus = new MBassador<>(
    new BusConfiguration()
        .setProperty(IBusConfiguration.Properties.BusId, "high-perf")
        .addFeature(Feature.AsynchronousMessageDispatch.Default())
        .addFeature(Feature.SyncPubSub.Default())
        .addFeature(Feature.AsynchronousHandlerInvocation.Default())
);
6. Comparison with Other Event Buses
    Feature	Mbassador	Guava EventBus	Spring Events
    Sync/Async	Both	Sync only	Both
    Weak References	Yes	Yes	No
    Event Filtering	Yes	No	No
    Priority Handling	Yes	No	Yes
    Performance	~20M/s	~5M/s	~2M/s
When to Use Mbassador
    High-throughput event systems (e.g., trading platforms)
    Modular applications where components need loose coupling
    Complex event routing with filters/priorities
    Lightweight projects where Spring is overkill

When to Avoid
    Distributed systems (consider Kafka/RabbitMQ instead)
    Very simple apps where direct calls suffice
    Projects already using Spring (use ApplicationEvent instead)

Example Project Structure
Copy
src/
├── main/
│   ├── java/
│   │   ├── events/
│   │   │   ├── OrderCreatedEvent.java
│   │   │   └── PaymentProcessedEvent.java
│   │   ├── handlers/
│   │   │   ├── NotificationHandler.java
│   │   │   └── LoggingHandler.java
│   │   └── App.java
Would you like a complete sample project with Spring Boot integration?


1. Dependencies
2. Event Bus Configuration
3. Event Classes
4. Event Handlers
5. Services Publishing Events
6. Main Application
7. Testing the Implementation
8. Advanced Configuration (Optional)


Custom Error Handler
    @Component
    public class GlobalErrorHandler {

        @Handler
        public void handleErrors(Throwable error) {
            System.err.println("Error in event handling: " + error.getMessage());
        }
    }
Conditional Event Handling
    @Handler
    @Filter("event.amount > 1000")
    public void handleLargePayment(PaymentProcessedEvent event) {
        System.out.println("Large payment detected!");
    }
Dead Event Handling
    @Handler
    public void handleDeadEvent(DeadEvent deadEvent) {
        System.err.println("No subscribers for: " + deadEvent.getMessage());
    }
9. Application Properties
    # application.properties
    # Enable/disable async processing
    mbassador.async.enabled=true

    # Thread pool configuration (for async handling)
    mbassador.async.threads.core=4
    mbassador.async.threads.max=8
    mbassador.async.queue-capacity=10000
10. Running the Application
When you run the application, you should see output like:
    Creating order: ORD-123
    [Audit] Order created: ORD-123
    [Notification] Order ORD-123 created for customer@example.com
    Payment succeeded for order ORD-123
    [Notification] Payment processed for order ORD-123: $99.99
Key Features Demonstrated
    Spring Boot Integration: Mbassador as a Spring-managed bean
    Synchronous/Asynchronous Handling: Mix of both approaches
    Priority Handling: Audit handler executes first
    Conditional Handling: Only successful payments trigger notifications
    Dependency Injection: Clean integration with Spring services
    Testability: Easy to mock and verify event handling

Best Practices
    Keep events immutable: Use final fields or Lombok @Value
    Separate concerns: Different handlers for different purposes
    Use async wisely: Only for non-critical or long-running tasks
    Monitor dead events: Helps identify unhandled events
    Consider error handling: Global error handler for robustness
Would you like me to extend this example with any specific features like:
    Distributed event handling
    Transactional event publishing
    Performance monitoring
    Spring Boot Actuator integration?

*/
