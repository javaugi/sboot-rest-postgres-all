package com.spring5.filestoragesvc;

import com.spring5.filestoragesvc.websvc.StandardConfigProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 *
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class StandardFileService implements FileService {

    private static final Logger log = LoggerFactory.getLogger(StandardFileService.class);
    private static final String STORED_FILE_ID_WITH_TOTAL_BYTES_WRITTEN = "Stored file ID: {} with {} total bytes written";
    private static final String ERROR_STORING_FILE_ID = "Error storing file ID: {}";
    private static final String GETTING_FILE_ID_FAILED = "Getting File ID: {} failed ";
    private static final int BUFFER_SIZE = 1000;

    @Autowired
    private ApplicationContext context;

    private class FileLocation {

        private final String directoryPath;
        private final StringBuilder guidPath;
        private final StringBuilder filePath;

        public FileLocation(String guid, String fileName) {
            directoryPath = context.getBean(StandardConfigProperties.class).getDirectoryPath();
            guidPath = new StringBuilder(directoryPath);
            guidPath.append(File.separator).append(guid);
            filePath = new StringBuilder(guidPath);
            filePath.append(File.separator).append(fileName);
        }

        public String getDirectoryPath() {
            return directoryPath;
        }

        public String getGuidPath() {
            return guidPath.toString();
        }

        public String getFilePath() {
            return filePath.toString();
        }
    }

    @Override
    public boolean storeFile(String guid, String fileName, String contentType, InputStream inputStream) {
        boolean fileStored = true;
        FileLocation fileLocation = new FileLocation(guid, fileName);
        byte[] buffer = new byte[BUFFER_SIZE];
        new File(fileLocation.getGuidPath()).mkdir();
        File outputFile = new File(fileLocation.getFilePath());

        int totalBytes = 0;
        try (FileOutputStream writer = new FileOutputStream(outputFile)) {

            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                if (bytesRead < BUFFER_SIZE) {
                    buffer = Arrays.copyOf(buffer, bytesRead);
                }
                writer.write(buffer);
                totalBytes += bytesRead;
            }

            writer.flush();
        } catch (IOException e) {
            fileStored = false;
            log.error(ERROR_STORING_FILE_ID, guid, e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                fileStored = false;
                log.error(ERROR_STORING_FILE_ID, guid, e);
            }
        }
        log.info(STORED_FILE_ID_WITH_TOTAL_BYTES_WRITTEN, guid, totalBytes);
        return fileStored;
    }

    @Override
    public InputStream getFile(String guid) {

        FileLocation fileLocation = new FileLocation(guid, getFilename(guid));
        try {
            File file = new File(fileLocation.getFilePath());
            return new FileInputStream(file);

        } catch (IOException ex) {
            log.error(GETTING_FILE_ID_FAILED, guid, ex);
            return null;
        }

    }

    @Override
    public boolean deleteFile(String guid) {
        FileLocation fileLocation = new FileLocation(guid, getFilename(guid));
        File file = new File(fileLocation.getFilePath());
        if (file.delete()) {
            File directory = new File(fileLocation.getGuidPath());
            return directory.delete();
        } else {
            return false;
        }
    }

    @Override
    public boolean fileExists(String guid) {
        FileLocation fileLocation = new FileLocation(guid, getFilename(guid));
        File file = new File(fileLocation.getFilePath());
        return file.exists();
    }

    @Override
    public String getContentType(String guid) {
        return URLConnection.guessContentTypeFromName(getFilename(guid));
    }

    @Override
    public String getFilename(String guid) {
        String directoryPath = context.getBean(StandardConfigProperties.class).getDirectoryPath();
        StringBuilder guidPath = new StringBuilder(directoryPath);
        guidPath.append(File.separator).append(guid);
        File guidDir = new File(guidPath.toString());
        String[] filenames = guidDir.list();
        if (filenames.length != 1) {
            log.warn("Found multiple files in a guid directory.  There should be one file per directory");
        }
        if (filenames.length > 0) {
            return filenames[0];
        } else {
            return "";
        }
    }
}
