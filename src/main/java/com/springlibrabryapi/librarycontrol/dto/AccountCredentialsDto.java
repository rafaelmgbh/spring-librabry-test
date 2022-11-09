package com.springlibrabryapi.librarycontrol.dto;

import java.io.Serializable;

public class AccountCredentialsDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;

    public AccountCredentialsDto(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
