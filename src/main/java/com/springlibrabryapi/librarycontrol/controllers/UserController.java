package com.springlibrabryapi.librarycontrol.controllers;

import com.springlibrabryapi.librarycontrol.dto.UserDto;
import com.springlibrabryapi.librarycontrol.dto.UserRoleDto;
import com.springlibrabryapi.librarycontrol.models.RoleModel;
import com.springlibrabryapi.librarycontrol.models.UserModel;
import com.springlibrabryapi.librarycontrol.models.UserRoleModel;
import com.springlibrabryapi.librarycontrol.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }


    @PostMapping()
    public ResponseEntity<Object> saveUser(@RequestBody UserDto userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already registered");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));

    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleModel>> getAllRoles() {


        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllRoles());


    }


    @PostMapping("/roles/auth")
    public ResponseEntity<Object> saveAuth(@RequestBody UserRoleDto userRoleDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.createAuth(userRoleDto));
    }

    @GetMapping("/roles/auth")
    public ResponseEntity<List<UserRoleModel>> getAllAuth() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllAuth());
    }

}