package com.server.app.dto.user;

import com.server.app.dto.role.RoleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private RoleResponseDto role;
}
