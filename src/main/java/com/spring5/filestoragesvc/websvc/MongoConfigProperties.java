/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc.websvc;

import com.spring5.filestoragesvc.FileService;
import com.spring5.filestoragesvc.MongoFileService;
import static com.spring5.filestoragesvc.websvc.MongoConfigProperties.MONGO_PROFILE;
import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author bill
 */
@Profile(MONGO_PROFILE)
@Configuration
@ConfigurationProperties(prefix = "com.spring5")
public class MongoConfigProperties implements Serializable {

    public static final String MONGO_PROFILE = "mongo";
    private static final Logger log = LoggerFactory.getLogger(MongoConfigProperties.class);

    private static final int DEFAULT_MONGO_PORT = 27017;

    @Bean
    public FileService getFileService() {
        log.info("CONFIG - Using MongoFileService for data storage");
        log.info("CONFIG - Mongo Username: {}", getMongoUsername());
        for (String serverName : getMongoServerList()) {
            log.info("CONFIG - Mongo Server: {}", serverName);
        }
        log.info("CONFIG - Mongo Port: {}", getMongoServerPort());
        return new MongoFileService();
    }

    @NotBlank
    private String mongoUsername;

    @NotBlank
    private String mongoPassword;

    @NotEmpty
    private String[] mongoServerList;

    @NotBlank
    private String mongoRepositoryName;

    private Integer mongoServerPort = null;

    public String getMongoUsername() {
        return mongoUsername;
    }

    public void setMongoUsername(String mongoUsername) {
        this.mongoUsername = mongoUsername;
    }

    public String getMongoPassword() {
        return mongoPassword;
    }

    public void setMongoPassword(String mongoPassword) {
        this.mongoPassword = mongoPassword;
    }

    public String[] getMongoServerList() {
        return mongoServerList;
    }

    public void setMongoServerList(String[] mongoServerList) {
        this.mongoServerList = mongoServerList;
    }

    public String getMongoRepositoryName() {
        return mongoRepositoryName;
    }

    public void setMongoRepositoryName(String mongoRepositoryName) {
        this.mongoRepositoryName = mongoRepositoryName;
    }

    public Integer getMongoServerPort() {
        if (mongoServerPort != null) {
            return mongoServerPort;
        } else {
            return DEFAULT_MONGO_PORT;
        }
    }

    public void setMongoServerPort(int mongoServerPort) {
        this.mongoServerPort = mongoServerPort;
    }
}
