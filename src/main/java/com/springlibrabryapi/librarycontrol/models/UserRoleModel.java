package com.springlibrabryapi.librarycontrol.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity


public class UserRoleModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private java.util.UUID userId;
    private java.util.UUID roleId;



    public java.util.UUID getUserId() {
        return userId;
    }

    public void setUserId(java.util.UUID userId) {
        this.userId = userId;
    }

    public java.util.UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(java.util.UUID roleId) {
        this.roleId = roleId;
    }
}

