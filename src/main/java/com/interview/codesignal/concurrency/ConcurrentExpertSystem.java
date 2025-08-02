/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * Explanation:
 *
 * 1. Expert System Design: Fact Class: Represents a piece of information or a
 * condition. It has a name (e.g., "temperature", "cough") and a value. Rule
 * Interface: Defines the contract for a rule. It has two methods:
 * evaluate(Set<Fact> facts): Takes a set of facts as input and returns true if
 * the rule's conditions are met, false otherwise. getConclusion(): Returns the
 * conclusion or diagnosis if the rule evaluates to true. Example Rules
 * (PossibleFluRule, PossibleCommonColdRule): These are concrete implementations
 * of the Rule interface. They define specific conditions based on the input
 * facts and provide a corresponding conclusion.
 *
 * 2. Concurrency: DiagnosisRequest Class: Represents a single request for
 * diagnosis, containing a unique requestId and a Set of symptoms (represented
 * as Fact objects). DiagnosisTask Class: Implements the Callable interface,
 * making it suitable for execution by an ExecutorService. Each DiagnosisTask is
 * responsible for processing a single DiagnosisRequest. The call() method
 * iterates through the list of rules and evaluates each rule against the
 * provided facts. If a rule evaluates to true, its conclusion is added to a
 * list of possible diagnoses. The method returns a formatted string indicating
 * the diagnosis result for the specific request. ConcurrentExpertSystem.main()
 * Method: Expert System Setup: Creates a list of diagnosticRules. In a real
 * system, these rules might be loaded from a file or database. Concurrent
 * Processing: Creates a list of DiagnosisRequest objects, each representing a
 * different patient's symptoms. An ExecutorService with a fixed number of
 * threads is created to manage the concurrent execution of tasks. The number of
 * threads is limited to the number of requests or the available processors,
 * whichever is smaller. For each DiagnosisRequest, a new DiagnosisTask is
 * created and submitted to the executorService. The submit() method returns a
 * Future object, which represents the result of the asynchronous computation.
 * The code then iterates through the list of Future objects, calling
 * future.get() to retrieve the result of each diagnosis task. This is a
 * blocking call, meaning the main thread will wait for each task to complete
 * before proceeding. Finally, the executorService is shut down to release the
 * resources. How this example demonstrates Expert System Design and
 * Concurrency: Expert System Design: The code follows the basic structure of a
 * rule-based expert system: Knowledge Base: The diagnosticRules list acts as a
 * simple knowledge base, containing the diagnostic rules. Facts: The Fact
 * objects represent the input data for the system. Inference Engine: The logic
 * within the DiagnosisTask that iterates through the rules and applies them to
 * the facts acts as a basic inference engine. Concurrency: The example uses
 * Java's concurrency features (ExecutorService, Callable, Future) to process
 * multiple diagnosis requests in parallel. This can significantly improve the
 * performance of the system, especially when dealing with a large number of
 * independent cases. Each diagnosis request is handled by a separate thread,
 * allowing for faster overall processing. Further Enhancements (Beyond this
 * basic example): More Sophisticated Rule Engine: For more complex expert
 * systems, you might use a dedicated rule engine like Drools or Apache Jena.
 * More Complex Fact Representation: Facts could be more structured, potentially
 * involving relationships between different pieces of information. Different
 * Concurrency Patterns: Depending on the specific requirements, you might
 * explore other concurrency patterns like using a thread pool with a different
 * scheduling policy or using reactive programming. Handling Uncertainty:
 * Real-world expert systems often need to deal with uncertainty. This could
 * involve using probabilistic rules or fuzzy logic. Explanation Facilities: A
 * good expert system should be able to explain its reasoning. This could
 * involve tracking which rules were triggered to reach a particular conclusion.
 * This example provides a foundational understanding of how you can combine
 * expert system design principles with concurrency in Java. The specific
 * implementation details will vary greatly depending on the complexity and
 * requirements of your actual application.
 *
 * @author javaugi
 */
public class ConcurrentExpertSystem {

    // --- Expert System Setup ---
    // --- Multiple Rules ---
    private final List<Rule> diagnosticRules = List.of(
                new PossibleFluRule(),
                new PossibleCommonColdRule()
        );
    // --- Concurrent Processing of Multiple Diagnosis Requests ---
    private final List<DiagnosisRequest> requests = List.of(
                new DiagnosisRequest(1, Set.of(new Fact("temperature", 39.2), new Fact("cough", true))),
                new DiagnosisRequest(2, Set.of(new Fact("soreThroat", true), new Fact("runnyNose", true))),
                new DiagnosisRequest(3, Set.of(new Fact("temperature", 37.0), new Fact("cough", false))),
                new DiagnosisRequest(4, Set.of(new Fact("temperature", 38.5), new Fact("cough", true), new Fact("soreThroat", false)))
        );
    private final int numberOfThreads = Math.min(requests.size(), Runtime.getRuntime().availableProcessors());
    private final ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
    
    
    private final List<Future<String>> futures = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ConcurrentExpertSystem main = new ConcurrentExpertSystem();
        main.run();
    }

    private void run() throws InterruptedException, ExecutionException {
        processRequests();
        checkResults();
        finalCleanup();
    }

    private void finalCleanup() {
        executorService.shutdown();
    }

    private void processRequests() {
        for (DiagnosisRequest request : requests) {
            Callable<String> task = new DiagnosisTask(request, diagnosticRules);
            Future<String> future = executorService.submit(task);
            futures.add(future);
        }
    }

    private void checkResults() throws ExecutionException, InterruptedException {
        // Retrieve and print the results
        System.out.println("\n--- Diagnosis Results ---");
        for (Future<String> future : futures) {
            System.out.println(future.get()); // Blocking call to get the result
        }
    }

}
