package com.server.app.dto.account;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "El alias es requerido")
    private String alias;

    @NotBlank(message = "La moneda es requerido")
    private String currency;

    @DecimalMin(value = "0.0", message = "El balance no puede ser negativo")
    private Double baseBalance;

    @Pattern(regexp = "^(Savings|Checking)$", message = "El tipo de cuenta puede ser de ahorro o corriente")
    private String type;
}