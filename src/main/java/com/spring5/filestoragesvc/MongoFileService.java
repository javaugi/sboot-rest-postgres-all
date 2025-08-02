package com.spring5.filestoragesvc;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.*;
import com.mongodb.client.gridfs.model.*;
import com.mongodb.client.model.Filters;
import com.spring5.filestoragesvc.websvc.MongoConfigProperties;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.bson.conversions.Bson;
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
public class MongoFileService implements FileService {

    private static final Logger log = LoggerFactory.getLogger(MongoFileService.class);

    private static final String ERROR_STORING_FILE = "Error storing file with guid - {} - file exists already";
    private static final String ERROR_GETTING_OBJECT_ID = "Error getting ObjectId for guid - {} - too many ObjectIds returned";
    private static final String ERROR_GETTING_FILE = "Error getting file with guid - {} - file not returned";
    private static final String ERROR_DELETING_FILE = "Error deleting file with guid - {} - file not deleted";
    private static final String INIT_MONGO_FILE_SERVICE = "Init - MongoFileService";
    private static final String DESTOY_MONGO_FILE_SERVICE = "Destoy - MongoFileService";
    private static final String DOC_METADATA_ID_FIELD = "_id";
    private static final String METADATA_ID = "metadata._id";
    private static final String FILENAME = "filename";
    private static final String CONTENT_TYPE = "contentType";
    private static final String MONGO_AUTHENTICATION_DATABASE = "admin";

    private MongoClient mongoClient;
    private GridFSBucket gridFSBucket;

    @Autowired
    private ApplicationContext context;

    private ObjectId getObjectId(String guid) {
        ObjectId objectId = null;
        Bson filterQuery = Filters.eq(METADATA_ID, guid);
        GridFSFindIterable gridFiles = gridFSBucket.find(filterQuery);
        int numberOfFilesFound = 0;
        for (GridFSFile gridFile : gridFiles) {
            objectId = gridFile.getObjectId();
            numberOfFilesFound++;
        }

        switch (numberOfFilesFound) {
            case 1 -> {
                return objectId;
            }
            case 0 -> {
                return null;
            }
            default -> {
                log.error(ERROR_GETTING_OBJECT_ID, guid);
                return null;
            }
        }
    }

    @Override
    public boolean storeFile(String guid, String fileName, String contentType, InputStream inputStream) {
        ObjectId foundObject = getObjectId(guid);
        if (foundObject != null) {
            log.error(ERROR_STORING_FILE, guid);
            return false;
        } else {
            Document doc = new Document(DOC_METADATA_ID_FIELD, guid);
            doc.put(CONTENT_TYPE, contentType);

            GridFSUploadOptions options = new GridFSUploadOptions()
                    .chunkSizeBytes(358400)
                    .metadata(doc);

            gridFSBucket.uploadFromStream(fileName, inputStream, options);
            return true;
        }
    }

    @Override
    public boolean deleteFile(String guid) {
        ObjectId foundObject = getObjectId(guid);
        if (foundObject != null) {
            gridFSBucket.delete(foundObject);
            return true;
        }
        log.error(ERROR_DELETING_FILE, guid);
        return false;
    }

    @Override
    public InputStream getFile(String guid) {
        ObjectId foundObject = getObjectId(guid);
        if (foundObject != null) {
            return gridFSBucket.openDownloadStream(foundObject);
        }
        log.error(ERROR_GETTING_FILE, guid);
        return null;
    }

    @Override
    public boolean fileExists(String guid) {
        ObjectId foundObject = getObjectId(guid);
        return (foundObject != null);
    }

    @Override
    public String getContentType(String guid) {
        Bson filterQuery = Filters.eq(METADATA_ID, guid);
        GridFSFindIterable gridFiles = gridFSBucket.find(filterQuery);
        String contentType = null;
        for (GridFSFile gridFile : gridFiles) {
            contentType = (String) gridFile.getMetadata().get(CONTENT_TYPE);
        }
        if (contentType == null) {
            return "";
        } else {
            return contentType;
        }
    }

    @Override
    public String getFilename(String guid) {
        Bson filterQuery = Filters.eq(METADATA_ID, guid);
        GridFSFindIterable gridFiles = gridFSBucket.find(filterQuery);
        for (GridFSFile gridFile : gridFiles) {
            return gridFile.getFilename();
        }
        return "";
    }

    @PreDestroy
    public void destory() {
        mongoClient.close();
        log.info(DESTOY_MONGO_FILE_SERVICE);
    }

    @PostConstruct
    public void init() {
        log.info(INIT_MONGO_FILE_SERVICE);
        MongoConfigProperties mongoConfigProperties = context.getBean(MongoConfigProperties.class);
        mongoClient = getMongoClient(mongoConfigProperties);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(mongoConfigProperties.getMongoRepositoryName());
        gridFSBucket = GridFSBuckets.create(mongoDatabase);
    }

    private MongoClient getMongoClient(MongoConfigProperties mongoConfigProperties) {
        List<ServerAddress> serverAddressList = new ArrayList();
        for (String serverName : mongoConfigProperties.getMongoServerList()) {
            serverAddressList.add(new ServerAddress(serverName, mongoConfigProperties.getMongoServerPort()));
        }
        MongoCredential mongoCredential = MongoCredential.createCredential(mongoConfigProperties.getMongoUsername(), MONGO_AUTHENTICATION_DATABASE, mongoConfigProperties.getMongoPassword().toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder()
                    .credential(mongoCredential) // Set the credential
                    .applyToClusterSettings(builder -> builder.hosts(serverAddressList)) // Set the server address(es)
                    // You can add other configurations here (e.g., connection pool settings, SSL)
                    // .applyToConnectionPoolSettings(...)
                    // .applyToSslSettings(...)
                    .build();

        return MongoClients.create(settings);
    }

}
