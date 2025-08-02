/*
 * Copyright (C) 2017 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc.websvc;

import com.spring5.filestoragesvc.FileService;
import com.spring5.filestoragesvc.StandardFileService;
import static com.spring5.filestoragesvc.websvc.StandardConfigProperties.FILE_PROFILE;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@Profile(FILE_PROFILE)
@Configuration
@ConfigurationProperties(prefix = "com.spring5")
public class StandardConfigProperties {

    public static final String FILE_PROFILE = "file";
    private static final Logger log = LoggerFactory.getLogger(StandardConfigProperties.class);

    @Bean
    public FileService getFileService() {
        log.info("CONFIG - Using StandardFileService for data storage");
        log.info("CONFIG - Directory Path: {}", getDirectoryPath());
        return new StandardFileService();
    }

    @NotBlank
    private String directoryPath;

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

}
