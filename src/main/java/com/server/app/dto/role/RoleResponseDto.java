package com.server.app.dto.role;

import com.server.app.dto.permission.PermissionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDto {
    private Long id;
    private String name;
    private List<PermissionResponseDto> permissions;
}
