package com.server.app.dto.auth;

import com.server.app.dto.permission.PermissionDto;
import com.server.app.dto.permission.PermissionResponseDto;
import com.server.app.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Se encarga del login y update profile
public class AuthResponseDto {
    private String token;
    private UserResponseDto UserData;
}
