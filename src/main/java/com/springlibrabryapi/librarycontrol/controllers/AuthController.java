package com.springlibrabryapi.librarycontrol.controllers;

import com.springlibrabryapi.librarycontrol.dto.AccountCredentialsDto;
import com.springlibrabryapi.librarycontrol.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @PostMapping(value="/signin")
    public ResponseEntity sigin(@RequestBody AccountCredentialsDto data){
        if (data.getEmail() == null || data.getPassword() == null){
            return ResponseEntity.badRequest().body("Username or password is null");
        }
        var token = authServices.sigin(data);
        if (token == null){
            return ResponseEntity.badRequest().body("Username or password is invalid");
        }else {
            return token;
        }

    }
}
