package com.server.app.services;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.auth.AuthResponseDto;
import com.server.app.dto.auth.LoginRequestDto;
import com.server.app.dto.auth.UpdatePasswordRequestDto;
import com.server.app.dto.permission.PermissionResponseDto;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserResponseDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.Role;
import com.server.app.entities.User;
import com.server.app.mapper.AuthMapper;
import com.server.app.repositories.RoleRepository;
import com.server.app.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JsonWebToken jsonWebToken;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    public AuthResponseDto login(LoginRequestDto request) {

        User user = userRepository.findUserByUsername(request.getUsername()) // Usa el método que tengas (findUserByUsername)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jsonWebToken.createToken(user);
        return authMapper.toAuthResponseDto(user, token);
    }


    public AuthResponseDto signup(UserCreateDto request) {
        if (userRepository.findUserByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        User newUser = authMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Error: No existe el rol ADMIN"));
        newUser.setRole(adminRole);

        userRepository.save(newUser);

        return buildAuthResponse(newUser);
    }

    public UserResponseDto getProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return authMapper.toUserResponseDTO(user);
    }

    @Transactional
    public UserResponseDto updateProfile(Integer userId, UserUpdateDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        userRepository.save(user);

        return authMapper.toUserResponseDTO(user);
    }

    @Transactional
    public AuthResponseDto updatePassword(Integer userId, UpdatePasswordRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getOldpassword(), user.getPassword())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        user.setPassword(passwordEncoder.encode(request.getNewpassword()));
        userRepository.save(user);
        return buildAuthResponse(user);
    }


    private AuthResponseDto buildAuthResponse(User user) {
        String token = jsonWebToken.createToken(user);

        UserResponseDto userData = authMapper.toUserResponseDTO(user);
        return new AuthResponseDto(token, userData);
    }
}
