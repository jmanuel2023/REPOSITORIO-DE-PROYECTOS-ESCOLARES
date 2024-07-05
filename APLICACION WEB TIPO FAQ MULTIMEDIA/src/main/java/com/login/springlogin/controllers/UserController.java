package com.login.springlogin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.springlogin.DTO.EmailDTO;
import com.login.springlogin.DTO.UserDTO;
import com.login.springlogin.services.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        System.out.println("creando");
        return ResponseEntity.ok().body(userService.save(userDTO));
    }	
    @PostMapping("/find")
    public ResponseEntity<?>  findByEmail(@RequestBody EmailDTO email) {
        return ResponseEntity.ok().body(userService.findByEmail(email.getEmail()));
        
    }
}
