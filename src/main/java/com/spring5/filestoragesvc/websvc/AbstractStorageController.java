/*
 * Copyright (C) 2020 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc.websvc;

import com.spring5.filestoragesvc.FileService;
import java.io.IOException;
import java.io.InputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * Common code for webservices to use.
 *
 * @author phillip
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public abstract class AbstractStorageController {

    private static final Logger log = LoggerFactory.getLogger(AbstractStorageController.class);

    public String defaultMethod() {
        return "App is running!";
    }

    public ResponseEntity uploadFile(
            String guid,
            MultipartFile uploadedFileRef,
            FileService fileService,
            String contentType) {

        String fileName = uploadedFileRef.getOriginalFilename();
        //to be HIPAA compliant avoid filename that may contain participant info
        log.info("UPLOAD - File ID: {} upload request", guid);

        InputStream inputStream;
        try {
            inputStream = uploadedFileRef.getInputStream();
        } catch (IOException ex) {
            log.error("UPLOAD - File ID: {} upload request failed: ", guid, ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (fileService.storeFile(guid, fileName, contentType, inputStream)) {
            log.info("UPLOAD - File ID: {} uploaded successfully!", guid);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.info("UPLOAD - File ID: {} FAILED to upload successfully!", guid);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Resource> downloadFile(
            String guid,
            FileService fileService) {

        log.info("DOWNLOAD - File ID: {} download request", guid);

        String fileName = fileService.getFilename(guid);
        InputStream inputStream = fileService.getFile(guid);
        String contentType = fileService.getContentType(guid);

        if (inputStream != null) {
            InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
            log.info("DOWNLOAD - File ID: {} downloaded successfully", guid);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", contentType);
            headers.add("Content-Disposition", "filename=\"" + fileName + "\"");
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } else {
            log.error("DOWNLOAD - File ID: {} NOT downloaded successfully", guid);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getFileInfo(
            String guid,
            FileService fileService) {

        log.info("FILE INFO - File ID: {} file info request", guid);

        String contentType = fileService.getContentType(guid);
        String fileName = fileService.getFilename(guid);
        if (StringUtils.isEmpty(fileName)) {
            log.error("FILE INFO - File ID: {} NOT found", guid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>("fileName=" + fileName + ";contentType=" + contentType, HttpStatus.OK);
        }
    }

    public ResponseEntity deleteFile(
            String guid,
            FileService fileService) {

        log.info("DELETE - File ID: {} delete request ", guid);
        if (fileService.deleteFile(guid)) {
            log.info("DELETE - File ID: {} deleted successfully", guid);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("DELETE - File ID: {} NOT deleted successfully", guid);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Resource> fileExists(
            String guid,
            FileService fileService) {

        boolean fileExists = fileService.fileExists(guid);
        log.info("EXISTS - File ID: {} exists {}", guid, fileExists);
        if (fileExists) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
