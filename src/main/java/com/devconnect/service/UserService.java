package com.devconnect.service;

import com.devconnect.dto.LoginRequestDTO;
import com.devconnect.dto.UserRequestDTO;
import com.devconnect.dto.UserResponseDTO;
import com.devconnect.entity.User;
import com.devconnect.repository.UserRepository;
import com.devconnect.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());

        User savedUser = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(savedUser.getId());
        response.setUsername(savedUser.getUsername());
        response.setEmail(savedUser.getEmail());

        return response;
    }

    public String loginUser(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return JwtUtil.generateToken(user.getEmail());
    }
}
