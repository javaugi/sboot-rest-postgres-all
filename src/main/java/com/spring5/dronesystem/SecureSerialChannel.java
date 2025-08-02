/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

/**
 *
 * @author javaugi
 */
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.*;
import java.security.spec.KeySpec;

public class SecureSerialChannel {

    private final SecretKey secretKey;
    private final Cipher cipher;
    private final IvParameterSpec ivParameterSpec;

    public SecureSerialChannel(String secret) throws GeneralSecurityException {
        // Derive key from secret using PBKDF2
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secret.toCharArray(), "salt".getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        this.secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

        // Initialize cipher with AES/CBC/PKCS5Padding
        this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        this.ivParameterSpec = new IvParameterSpec(iv);
    }

    public byte[] encrypt(SensorData data) throws IOException, GeneralSecurityException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(data);
        oos.flush();
        byte[] serialized = bos.toByteArray();

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(serialized);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(ivParameterSpec.getIV());
        outputStream.write(encrypted);

        return outputStream.toByteArray();
    }

    public SensorData decrypt(byte[] encryptedData) throws IOException, GeneralSecurityException, ClassNotFoundException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(encryptedData);
        byte[] iv = new byte[16];
        inputStream.read(iv);
        byte[] encryptedPayload = inputStream.readAllBytes();

        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(encryptedPayload);

        ByteArrayInputStream bis = new ByteArrayInputStream(decrypted);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (SensorData) ois.readObject();
    }
}
