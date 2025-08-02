/*
 * Copyright (C) 2017 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc.websvc;

import com.spring5.filestoragesvc.FileService;
import java.net.URLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
@RestController
public class StorageController extends AbstractStorageController {

    private static final Logger log = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/cim-file-service")
    public String defaultMethod() {
        return super.defaultMethod();
    }

    @RequestMapping(value = "/cim-file-service/upload")
    public ResponseEntity uploadFile(
            @RequestParam("guid") String guid,
            @RequestParam("uploadedFile") MultipartFile uploadedFileRef) {
        String fileName = uploadedFileRef.getOriginalFilename();
        return super.uploadFile(guid, uploadedFileRef, context.getBean(FileService.class), URLConnection.guessContentTypeFromName(fileName));
    }

    @GetMapping(value = "/cim-file-service/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam("guid") String guid,
            @RequestParam("fileName") String fileName) {
        return super.downloadFile(guid, context.getBean(FileService.class));
    }

    @RequestMapping(value = "/cim-file-service/delete")
    public ResponseEntity deleteFile(
            @RequestParam("guid") String guid,
            @RequestParam("fileName") String fileName) {
        return super.deleteFile(guid, context.getBean(FileService.class));
    }

    @GetMapping(value = "/cim-file-service/exists")
    public ResponseEntity<Resource> fileExists(
            @RequestParam("guid") String guid,
            @RequestParam("fileName") String fileName) {
        return super.fileExists(guid, context.getBean(FileService.class));
    }

}
