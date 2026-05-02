package com.devconnect.controller;

import com.devconnect.dto.LoginRequestDTO;
import com.devconnect.dto.UserRequestDTO;
import com.devconnect.dto.UserResponseDTO;
import com.devconnect.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return userService.loginUser(dto);
    }

    @GetMapping("/profiles")
    public String getProfiles() {
        return "Access grannted to protected endpoint!";
    }

    @GetMapping("/admin/test")
    public String adminOnly() {
        return "Admin access granted!";
    }
}
