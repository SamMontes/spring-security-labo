package com.server.app.controllers;

import com.server.app.config.JsonWebToken;
import com.server.app.dto.auth.AuthResponseDto;
import com.server.app.dto.auth.LoginRequestDto;
import com.server.app.dto.auth.UpdatePasswordRequestDto;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserResponseDto;
import com.server.app.dto.user.UserUpdateDto;
import com.server.app.entities.User;
import com.server.app.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private JsonWebToken jsonWebToken;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody UserCreateDto request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getProfile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(authService.getProfile(user.getId()));
    }


    @PutMapping("/update/profile")
    public ResponseEntity<UserResponseDto> updateProfile(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestBody UserUpdateDto request) {

        String token = tokenHeader.substring(7);
        Integer userId = jsonWebToken.extractIdUser(token);

        return ResponseEntity.ok(authService.updateProfile(userId, request));
    }

    @PutMapping("/update/password")
    public ResponseEntity<String> updatePassword(
            @RequestHeader("Authorization") String tokenHeader,
            @RequestBody UpdatePasswordRequestDto request) {

        String token = tokenHeader.substring(7);
        Integer userId = jsonWebToken.extractIdUser(token);

        authService.updatePassword(userId, request);
        return ResponseEntity.ok("Contraseña actualizada con éxito");
    }
}
