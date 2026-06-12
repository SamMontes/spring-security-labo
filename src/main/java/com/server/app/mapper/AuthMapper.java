package com.server.app.mapper;

import com.server.app.dto.auth.AuthResponseDto;
import com.server.app.dto.permission.PermissionResponseDto;
import com.server.app.dto.role.RoleResponseDto;
import com.server.app.dto.user.UserCreateDto;
import com.server.app.dto.user.UserResponseDto;
import com.server.app.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthMapper {
    /*public UserResponseDto toUserResponseDTO(User user) {
        if (user == null) return null;

        List<PermissionResponseDto> permissions = user.getRole().getPermissions().stream()
                .map(p -> new PermissionResponseDto(p.getId(), p.getPath(), p.getMethod()))
                .collect(Collectors.toList());

        RoleResponseDto roleResponse = new RoleResponseDto(
                user.getRole().getId(),
                user.getRole().getName(),
                permissions
        );

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                new RoleResponseDto()
        );
    }

    public User toEntity(UserCreateDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        return user;
    }*/
    // --- PARTE 1: Transformar Request (DTO -> Entity) ---
    // Este es el que usas al inicio de tu método signup en el Service
    public User toEntity(UserCreateDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // ¡No olvides el password!
        // El rol generalmente se asigna en el servicio buscando por nombre o ID,
        // así que no lo mapees aquí a menos que el DTO traiga el ID del rol.
        return user;
    }

    // --- PARTE 2: Transformar Response (Entity -> DTO) ---
    // Este es el que usas al final de tu método signup para enviar la respuesta
    public AuthResponseDto toAuthResponseDto(User user, String token) {
        return new AuthResponseDto(token, toUserResponseDTO(user));
    }

    public UserResponseDto toUserResponseDTO(User user) {
        if (user == null) return null;

        List<PermissionResponseDto> permissions = user.getRole().getPermissions().stream()
                .map(p -> new PermissionResponseDto(p.getId(), p.getPath(), p.getMethod()))
                .collect(Collectors.toList());

        RoleResponseDto roleResponse = new RoleResponseDto(
                user.getRole().getId(),
                user.getRole().getName(),
                permissions
        );

        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                roleResponse
        );
    }

}
