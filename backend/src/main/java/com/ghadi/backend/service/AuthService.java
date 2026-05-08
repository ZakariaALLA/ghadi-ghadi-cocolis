package com.ghadi.backend.service;

import com.ghadi.backend.dto.AuthRequest;
import com.ghadi.backend.dto.AuthResponse;
import com.ghadi.backend.dto.RegisterRequest;
import com.ghadi.backend.model.User;
import com.ghadi.backend.repository.UserRepository;
import com.ghadi.backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Split full name if possible
        String[] names = request.getFullName().split(" ", 2);
        user.setFirstName(names[0]);
        if (names.length > 1) {
            user.setLastName(names[1]);
        }

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtils.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail(), user.getRole());
    }
}
