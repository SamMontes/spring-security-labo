package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "categorias")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String type; // Ingreso o Egreso

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_padre_id")
    private Category parentCategory;
}
