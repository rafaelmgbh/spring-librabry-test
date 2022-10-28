package com.springlibrabryapi.librarycontrol.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
