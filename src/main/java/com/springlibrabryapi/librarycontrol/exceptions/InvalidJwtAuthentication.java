package com.springlibrabryapi.librarycontrol.exceptions;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthentication extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public InvalidJwtAuthentication(String exception) {
        super(exception);
    }

}
