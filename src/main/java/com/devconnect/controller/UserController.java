package com.devconnect.controller;

import com.devconnect.dto.LoginRequestDTO;
import com.devconnect.dto.UserRequestDTO;
import com.devconnect.dto.UserResponseDTO;
import com.devconnect.entity.User;
import com.devconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.createUser(userRequestDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
        return userService.loginUser();
    }
}
