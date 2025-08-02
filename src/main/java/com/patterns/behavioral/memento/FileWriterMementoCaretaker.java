/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.memento;

/**
 *
 * Notice that caretaker object contains the saved state in the form of Object,
 * so it can’t alter its data and also it has no knowledge of it’s structure.
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FileWriterMementoCaretaker {

    private Object obj;

    public void save(FileWriterUtilMementoOriginator fileWriter) {
        this.obj = fileWriter.save();
    }

    public void undo(FileWriterUtilMementoOriginator fileWriter) {
        fileWriter.undoToLastSave(obj);
    }
}
