package com.makemyrides.backend.service;

import com.makemyrides.backend.dto.UserRequestDTO;
import com.makemyrides.backend.dto.UserResponseDTO;
import com.makemyrides.backend.entity.User;
import com.makemyrides.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(dto.getPassword());
        User saved = userRepository.save(user);
        return modelMapper.map(saved, UserResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return modelMapper.map(user, UserResponseDTO.class);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        modelMapper.map(dto, user);

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword());
        }

        User updated = userRepository.save(user);
        return modelMapper.map(updated, UserResponseDTO.class);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
