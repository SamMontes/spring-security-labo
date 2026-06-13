package com.server.app.dto.movement;

import com.server.app.dto.account.AccountResponse;
import com.server.app.dto.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementResponse {
    private Integer id;
    private Double monto;
    private LocalDateTime fecha;
    private String descripcion;
    private AccountResponse cuenta;
    private CategoryResponse categoria;
}
