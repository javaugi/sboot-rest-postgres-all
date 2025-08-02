/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.milsys;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.security.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.*;
import javax.crypto.spec.*;

public class MilitaryConfigManager {
    private static final String CONFIG_DIR = "/secure/military/config";
    private static final String ENCRYPTION_KEY = "AES-256-Encryption-Key"; // In real system, use HSM
    
    private final Map<String, String> configMap = new ConcurrentHashMap<>();
    private final WatchService configWatcher;
    private final Thread watchThread;

    public MilitaryConfigManager() throws IOException {
        // Set up secure configuration directory
        Path configPath = Paths.get(CONFIG_DIR);
        if (!Files.exists(configPath)) {
            Files.createDirectories(configPath);
            Files.setPosixFilePermissions(configPath, 
                Set.of(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE,
                      PosixFilePermission.OWNER_EXECUTE));
        }
        
        this.configWatcher = FileSystems.getDefault().newWatchService();
        configPath.register(configWatcher, StandardWatchEventKinds.ENTRY_MODIFY);
        
        this.watchThread = new Thread(this::watchConfigChanges);
        watchThread.setDaemon(true);
        watchThread.start();
        
        loadAllConfigs();
    }

    private void loadAllConfigs()  {
        try {
            Files.list(Paths.get(CONFIG_DIR))
                 .filter(Files::isRegularFile)
                 .forEach(this::loadConfigFile);
        } catch (IOException e) {
            //throw new DefenseConfigException("Failed to load military configurations");
        }
    }

    private void loadConfigFile(Path configFile) {
        try {
            byte[] encryptedData = Files.readAllBytes(configFile);
            String decryptedContent = decryptConfig(encryptedData);
            
            Properties props = new Properties();
            props.load(new StringReader(decryptedContent));
            
            props.forEach((k, v) -> configMap.put(k.toString(), v.toString()));
        } catch (Exception e) {
            //throw new DefenseConfigException("Invalid config file: " + configFile, e);
        }
    }

    private String decryptConfig(byte[] encryptedData) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), "AES");
        GCMParameterSpec ivSpec = new GCMParameterSpec(128, new byte[12]); // 96-bit IV
        
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encryptedData);
        
        return new String(decrypted);
    }

    private void watchConfigChanges() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                WatchKey key = configWatcher.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changedFile = (Path) event.context();
                        loadConfigFile(Paths.get(CONFIG_DIR, changedFile.toString()));
                    }
                }
                key.reset();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                // Secure logging
            }
        }
    }
    
    public String getConfig(String key) {
        String value = configMap.get(key);
        if (value == null) {
            //throw new DefenseConfigException("Missing required military configuration: " + key);
        }
        return value;
    }
}
