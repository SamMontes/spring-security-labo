package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cuentas")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;
    private String currency;
    private Double baseBalance;
    private String type;

    @Column(name = "usuario_id")
    private Integer userId;
}