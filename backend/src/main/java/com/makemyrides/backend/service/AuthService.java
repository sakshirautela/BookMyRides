package com.makemyrides.backend.service;

import com.makemyrides.backend.dto.LoginRequestDTO;
import com.makemyrides.backend.dto.LoginResponseDTO;
import com.makemyrides.backend.entity.User;
import com.makemyrides.backend.repository.UserRepository;
import com.makemyrides.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponseDTO authenticate(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new LoginResponseDTO(token, user.getEmail());
    }
}
