/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.milsys;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Defense-related coding example showing:
 * - Secure message processing
 * - Thread safety
 * - Exception handling
 * - Performance considerations
 */
public class SecureMessageProcessor {
    private static final String ALGORITHM = "AES";
    private final BlockingQueue<String> messageQueue;
    private final SecretKeySpec secretKey;

    public SecureMessageProcessor(String encryptionKey) throws Exception {
        this.messageQueue = new LinkedBlockingQueue<>();
        byte[] key = MessageDigest.getInstance("SHA-256")
                            .digest(encryptionKey.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(key, ALGORITHM);
    }

    // Thread-safe message submission
    public void submitMessage(String message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Message submission interrupted", e);
        }
    }

    // Process messages securely
    public void processMessages() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                String message = messageQueue.take();
                String encrypted = encrypt(message);
                // In real system, would send to secure channel
                System.out.println("Processed: " + encrypted);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Log security exception appropriately
                System.err.println("Security processing error: " + e.getMessage());
            }
        }
    }

    private String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Unit test would verify this functionality
    public static void main(String[] args) throws Exception {
        SecureMessageProcessor processor = new SecureMessageProcessor("secure-defense-key123");
        
        // Simulate message submission
        new Thread(() -> {
            processor.submitMessage("Sensor1:AllClear");
            processor.submitMessage("Sensor2:MovementDetected");
        }).start();

        // Process messages
        processor.processMessages();
    }
}
