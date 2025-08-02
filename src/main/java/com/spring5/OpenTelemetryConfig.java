/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import static com.google.api.expr.v1alpha1.ConformanceServiceGrpc.SERVICE_NAME;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
//import io.opentelemetry.exporter.prometheus.PrometheusHttpServer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author javaugi
 */
@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        return OpenTelemetrySdk.builder()
                .setTracerProvider(
                        SdkTracerProvider.builder()
                                .addSpanProcessor(SimpleSpanProcessor.create(new LoggingSpanExporter()))
                                .build())
                .build();
    }

    @Bean
    public Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer("com.spring5.kafkamicroservice");
    }

    /**
     * Initializes the OpenTelemetry SDK and configures the prometheus collector
     * with all default settings.
     *
     * @param prometheusPort the port to open up for scraping.
     * @return a ready-to-use {@link OpenTelemetry} instance.
     */
    static OpenTelemetry initOpenTelemetry(int prometheusPort) {
        // Include required service.name resource attribute on all spans and metrics
        Resource resource
                = Resource.getDefault()
                        .merge(Resource.builder().put(SERVICE_NAME, "PrometheusExporterExample").build());

        OpenTelemetrySdk openTelemetrySdk
                = OpenTelemetrySdk.builder()
                        .setTracerProvider(
                                SdkTracerProvider.builder()
                                        .setResource(resource)
                                        .addSpanProcessor(SimpleSpanProcessor.create(LoggingSpanExporter.create()))
                                        .build())
                        .setMeterProvider(
                                SdkMeterProvider.builder()
                                        .setResource(resource)
                                        //.registerMetricReader(
                                        //    PrometheusHttpServer.builder().setPort(prometheusPort).build()
                                        //)
                                        .build())
                        .buildAndRegisterGlobal();

        Runtime.getRuntime().addShutdownHook(new Thread(openTelemetrySdk::close));

        return openTelemetrySdk;
    }
}

/*
 that error means your Spring Boot app is expecting a bean of type io.opentelemetry.api.trace.Tracer, but it hasn’t been provided in the application context.

Let’s walk through how to fix it by registering a Tracer bean, step-by-step.

✅ Option 1: Auto-configure OpenTelemetry SDK (Recommended for Simplicity)
🔧 1. Add Required Dependencies
In your pom.xml:

xml
Copy
Edit
<!-- OpenTelemetry SDK -->
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-sdk</artifactId>
    <version>1.33.0</version> <!-- Or latest -->
</dependency>

<!-- OpenTelemetry API (included transitively, but you can declare it) -->
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-api</artifactId>
</dependency>

<!-- Optional: Exporter (console/logging, OTLP, etc.) -->
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-logging</artifactId>
</dependency>
✅ 2. Register Tracer Bean in Spring Config
Create a @Configuration class like:

java
Copy
Edit
package com.spring5.kafkamicroservice.config;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        return OpenTelemetrySdk.builder()
                .setTracerProvider(
                        SdkTracerProvider.builder()
                                .addSpanProcessor(SimpleSpanProcessor.create(new LoggingSpanExporter()))
                                .build())
                .build();
    }

    @Bean
    public Tracer tracer(OpenTelemetry openTelemetry) {
        return openTelemetry.getTracer("com.spring5.kafkamicroservice");
    }
}
This will register the Tracer bean that Spring can inject into your MonitoringController.

💡 Your MonitoringController Should Now Look Like:
java
Copy
Edit
@RestController
public class MonitoringController {

    private final SomeService someService;
    private final Tracer tracer;

    public MonitoringController(SomeService someService, Tracer tracer) {
        this.someService = someService;
        this.tracer = tracer;
    }

    // use tracer.spanBuilder(...) etc.
}
🧪 Bonus: Console Output
With the logging exporter, you should see something like this in the logs:

less
Copy
Edit
Span started: my-span-name
Span ended: my-span-name
🚀 Want Auto-Instrumentation?
If you're planning to use full OpenTelemetry auto-instrumentation (HTTP, Kafka, DB, etc.) and don’t want to manually configure the SDK, I can guide you through that too. Just let me know!

Let me know if you’re exporting to Jaeger, OTEL Collector, Zipkin, etc., and I’ll tweak the config.
 */
