package com.server.app.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "El nombre es requerido")
    private String name;

    @Pattern(regexp = "^(Ingreso|Egreso)$", message = "El tipo puede ser ingreso o egreso")
    private String type;

    private Integer parentCategoryId;
}
