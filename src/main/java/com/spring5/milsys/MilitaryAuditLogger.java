/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.milsys;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class MilitaryAuditLogger {
    private final BlockingQueue<AuditEntry> logQueue = new LinkedBlockingQueue<>();
    private final ExecutorService logExecutor = Executors.newSingleThreadExecutor();
    private final Path logDirectory;
    private final Cipher cipher;
    private final SecretKeySpec secretKey;
    
    private static final String LOG_PREFIX = "DMS"; // Defense Military System
    private static final String LOG_EXT = ".enc";
    private static final int MAX_LOG_SIZE = 10 * 1024 * 1024; // 10MB per file

    public MilitaryAuditLogger(String baseDir) throws GeneralSecurityException, IOException {
        this.logDirectory = Paths.get(baseDir, "secure_logs");
        Files.createDirectories(logDirectory);
        
        // Set military-grade encryption
        this.cipher = Cipher.getInstance("AES/GCM/NoPadding");
        this.secretKey = new SecretKeySpec(
            "DefenseLoggingKey123".getBytes(), "AES"); // In production, use HSM-managed key
        
        startLogProcessor();
        Runtime.getRuntime().addShutdownHook(new Thread(this::flushOnShutdown));
    }

    public void logSecurityEvent(String eventType, String message, String userId) {
        AuditEntry entry = new AuditEntry(
            Instant.now(),
            eventType,
            message,
            userId,
            Thread.currentThread().getName(),
            getCallerClass()
        );
        
        if (!logQueue.offer(entry)) {
            // Emergency fallback logging
            System.err.println("CRITICAL: Audit log queue overflow - " + entry);
        }
    }

    private void startLogProcessor() {
        logExecutor.submit(() -> {
            Path currentLogFile = null;
            OutputStream currentOutput = null;
            
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    AuditEntry entry = logQueue.poll(100, TimeUnit.MILLISECONDS);
                    
                    if (entry != null) {
                        // Rotate log file if needed
                        if (currentLogFile == null || 
                            (currentOutput != null && Files.size(currentLogFile) > MAX_LOG_SIZE)) {
                            
                            if (currentOutput != null) {
                                currentOutput.close();
                            }
                            
                            currentLogFile = getNewLogFile();
                            currentOutput = getEncryptedOutput(currentLogFile);
                        }
                        
                        // Write encrypted log entry
                        byte[] logData = entry.toString().getBytes();
                        byte[] encrypted = encryptLogEntry(logData);
                        
                        currentOutput.write(encrypted);
                        currentOutput.write('\n');
                        currentOutput.flush();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Critical failure handling
            } finally {
                try {
                    if (currentOutput != null) currentOutput.close();
                } catch (IOException e) {
                    // Handle
                }
            }
        });
    }

    private byte[] encryptLogEntry(byte[] data) throws GeneralSecurityException {
        byte[] iv = new byte[12]; // 96-bit IV
        new SecureRandom().nextBytes(iv);
        
        GCMParameterSpec ivSpec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        
        byte[] encrypted = cipher.doFinal(data);
        
        // Prepend IV to encrypted data
        byte[] result = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
        
        return result;
    }

    private Path getNewLogFile() throws IOException {
        String timestamp = Instant.now().toString().replace(":", "");
        return Files.createFile(logDirectory.resolve(LOG_PREFIX + timestamp + LOG_EXT));
    }

    private OutputStream getEncryptedOutput(Path file) throws IOException {
        return Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    private void flushOnShutdown() {
        logExecutor.shutdown();
        try {
            if (!logExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
                logExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            logExecutor.shutdownNow();
        }
    }

    private String getCallerClass() {
        // Implementation would use StackWalker in Java 9+
        return Arrays.stream(Thread.currentThread().getStackTrace())
            .skip(3)
            .findFirst()
            .map(StackTraceElement::getClassName)
            .orElse("UNKNOWN");
    }

    private static class AuditEntry {
        private final Instant timestamp;
        private final String eventType;
        private final String message;
        private final String userId;
        private final String threadName;
        private final String sourceClass;
        
        public AuditEntry(Instant timestamp, String eventType, String message, 
                         String userId, String threadName, String sourceClass) {
            this.timestamp = timestamp;
            this.eventType = eventType;
            this.message = message;
            this.userId = userId;
            this.threadName = threadName;
            this.sourceClass = sourceClass;
        }
        
        @Override
        public String toString() {
            return String.format("%s|%s|%s|%s|%s|%s|%s",
                LOG_PREFIX,
                timestamp.toString(),
                eventType,
                userId,
                threadName,
                sourceClass,
                message.replace("|", "\\|"));
        }
    }
}
