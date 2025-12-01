package com.banco.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount; // Monto
    private String category; // Tipo ("personal", "hipotecario")
    private String status; // Estado ("pendiente", "aprobado")
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}