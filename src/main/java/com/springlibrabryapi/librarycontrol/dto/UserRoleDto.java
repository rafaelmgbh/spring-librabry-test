package com.springlibrabryapi.librarycontrol.dto;

import java.util.UUID;

public class UserRoleDto {

    private UUID userId;
    private UUID roleId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }


}
