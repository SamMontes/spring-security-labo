package com.server.app.dto.movement;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovementRequest {
    @NotNull(message = "LA cnatidad es requerida")
    private Double amount;

    @NotBlank(message = "La moneda es requerida")
    private String originalCurrency;

    @DecimalMin(value = "0.0001", message = "La tasa de cambio debe ser positiva")
    private Double exchangeRate;

    @NotNull(message = "LA fecha es requerida")
    private LocalDateTime date;

    @NotBlank(message = "La descripcion es requerida")
    private String description;

    @NotNull(message = "El ID de la cuneta es requerida")
    private Integer accountId;

    @NotNull(message = "El ID de la categoria es requerida")
    private Integer categoryId;
}
