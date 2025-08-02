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
public class PermissionDescriptor {

    private static final Logger log = LoggerFactory.getLogger(PermissionDescriptor.class);

    /**
     * Only these values can be set as a GrantPolicy to PermissionDescriptor.
     */
    public enum GrantPolicy {
        GRANT_POLICY_NEVER,
        GRANT_POLICY_IMMEDIATELY,
        GRANT_POLICY_ON_REQUEST,
        GRANT_POLICY_ON_SECOND_REQUEST
    }

    public enum PermissionPolicy {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }

    public final PermissionPolicy permission;
    public final GrantPolicy grantPolicy;

    public PermissionDescriptor(PermissionPolicy permission, GrantPolicy grantPolicy) {
        this.permission = permission;
        this.grantPolicy = grantPolicy;
    }

}
