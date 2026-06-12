package com.server.app.dto.auth;

import com.server.app.dto.permission.PermissionDto;
import com.server.app.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Se encarga de visualizar el perfil y el cambio de contraseña
public class ProfileResponseDto {
    private UserResponseDto UserData;
    private PermissionDto PermissionData;
}
