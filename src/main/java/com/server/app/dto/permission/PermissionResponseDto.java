package com.server.app.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDto {
    private Long id;
    private String path;
    private String method;
}
