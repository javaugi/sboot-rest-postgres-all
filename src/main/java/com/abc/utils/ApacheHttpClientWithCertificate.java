/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.abc.utils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author javaugi
 */
public class ApacheHttpClientWithCertificate {

    public static void main(String[] args) throws Exception {
        // Replace with your keystore file path and password
        String keystorePath = "path/to/your/client.jks";
        String keystorePassword = "your_keystore_password";

        // Replace with the password for your private key (can be the same as keystore password)
        String keyPassword = "your_key_password";

        // Replace with the URL of the HTTPS endpoint requiring client certificate authentication
        String targetUrl = "https://your-secured-endpoint.com";

        try (CloseableHttpClient httpClient = createHttpClientWithCertificate(keystorePath, keystorePassword, keyPassword)) {
            HttpGet httpGet = new HttpGet(targetUrl);

            System.out.println("Executing request: " + httpGet.getMethod() + " " + httpGet.getURI());

            httpClient.execute(httpGet, response -> {
                int statusCode = response.getStatusLine().getStatusCode();
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println("Status Code: " + statusCode);
                if (entity != null) {
                    System.out.println("Response Body:");
                    System.out.println(EntityUtils.toString(entity));
                }
                System.out.println("----------------------------------------");
                return statusCode;
            });

        } catch (Exception e) {
            System.err.println("Error during HTTPS request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient createHttpClientWithCertificate(String keystorePath, String keystorePassword, String keyPassword)
            throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {

        SSLContext sslContext;
        try {
            KeyStore clientKeystore = KeyStore.getInstance("JKS"); // Or "PKCS12" depending on your keystore type
            try (FileInputStream fis = new FileInputStream(new File(keystorePath))) {
                clientKeystore.load(fis, keystorePassword.toCharArray());
            }

            SSLContextBuilder sslContextBuilder = SSLContextBuilder.create()
                    .loadKeyMaterial(clientKeystore, keyPassword.toCharArray()) // Optional: Trust all server certificates (for testing only, NOT recommended for production)
                    // .setTrustStrategy(TrustStrategy.ACCEPT_ALL)
                    // Recommended: Load a truststore with the server's certificate or CA
                    // .loadTrustMaterial(loadTrustStore("path/to/your/truststore.jks", "truststore_password"), null)
                    ;
            sslContext = sslContextBuilder.build();

        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
            throw new IOException("Failed to load client certificate keystore: " + e.getMessage(), e);
        }

        //HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
        /*
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSslContext(sslContext)
                .setDefaultConnectionConfig(ConnectionConfig.custom()
                        .setConnectTimeout(java.time.Duration.ofSeconds(10))
                        .setSocketTimeout(java.time.Duration.ofSeconds(10))
                        .build())
                .build();

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
        // */
        return null;
    }

    // Optional: Method to load a truststore
    private static KeyStore loadTrustStore(String truststorePath, String truststorePassword)
            throws IOException, CertificateException, NoSuchAlgorithmException, KeyStoreException {
        KeyStore truststore = KeyStore.getInstance("JKS"); // Or "PKCS12"
        try (FileInputStream fis = new FileInputStream(new File(truststorePath))) {
            truststore.load(fis, truststorePassword.toCharArray());
        }
        return truststore;
    }
}
