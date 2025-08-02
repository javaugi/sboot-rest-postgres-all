/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.concurrent.*;

public class CommunicationManager {

    private final SecureSerialChannel secureChannel;
    private final ExecutorService executor;
    private ServerSocket serverSocket;
    private volatile boolean running = true;

    public CommunicationManager(String secret) throws GeneralSecurityException {
        this.secureChannel = new SecureSerialChannel(secret);
        this.executor = Executors.newCachedThreadPool();
    }

    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        executor.submit(() -> {
            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                } catch (IOException e) {
                    if (running) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void handleClient(Socket clientSocket) {
        executor.submit(() -> {
            try (InputStream is = clientSocket.getInputStream(); OutputStream os = clientSocket.getOutputStream()) {

                byte[] lengthBytes = new byte[4];
                is.read(lengthBytes);
                int length = ByteBuffer.wrap(lengthBytes).getInt();

                byte[] encryptedData = new byte[length];
                is.read(encryptedData);

                SensorData data = secureChannel.decrypt(encryptedData);
                // Process received data
                System.out.println("handleClient data=" + data);

                // Send acknowledgment
                byte[] ack = "ACK".getBytes();
                os.write(ack);
                os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendData(String host, int port, SensorData data) throws IOException, GeneralSecurityException {
        try (Socket socket = new Socket(host, port); OutputStream os = socket.getOutputStream()) {

            byte[] encrypted = secureChannel.encrypt(data);
            byte[] lengthBytes = ByteBuffer.allocate(4).putInt(encrypted.length).array();

            os.write(lengthBytes);
            os.write(encrypted);
            os.flush();

            // Read acknowledgment
            byte[] ack = new byte[3];
            socket.getInputStream().read(ack);
            if (!new String(ack).equals("ACK")) {
                throw new IOException("Did not receive proper acknowledgment");
            }
        }
    }

    public void shutdown() {
        running = false;
        executor.shutdown();
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (IOException | InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
