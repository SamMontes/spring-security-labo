package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Data
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;
    private String originalCurrency;
    private Double exchangeRate;
    private LocalDateTime date;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Category category;
}