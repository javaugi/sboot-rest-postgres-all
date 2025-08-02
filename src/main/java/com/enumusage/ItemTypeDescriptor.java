/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.enumusage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ItemTypeDescriptor {

    private static final Logger log = LoggerFactory.getLogger(ItemTypeDescriptor.class);
    // ... type definitions
    // Describes when the annotation will be discarded

    public enum ItemType {
        TYPE_MUSIC,
        TYPE_PHOTO,
        TYPE_TEXT
    }

    public final ItemType itemType;
    // Declare the constants
    public static final int TYPE_MUSIC = 0;
    public static final int TYPE_PHOTO = 1;
    public static final int TYPE_TEXT = 2;

    // Mark the argument as restricted to these enumerated types
    public ItemTypeDescriptor(ItemType itemType) {
        this.itemType = itemType;
    }
}
