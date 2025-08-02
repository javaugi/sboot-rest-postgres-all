/*
 * Copyright (C) 2017 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.filestoragesvc;

import java.io.InputStream;

/**
 *
 * @author bill
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface FileService {

    public boolean storeFile(String guid, String fileName, String contentType, InputStream inputStream);

    public boolean deleteFile(String guid);

    public InputStream getFile(String guid);

    public String getContentType(String guid);

    public String getFilename(String guid);

    public boolean fileExists(String guid);

}
