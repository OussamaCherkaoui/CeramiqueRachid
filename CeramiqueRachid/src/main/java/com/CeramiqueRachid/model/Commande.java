package com.CeramiqueRachid.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate dateCommande;
    @Column
    private String client;
    @Column
    private Double total;
    @Column
    private Boolean statut;
    @ManyToOne
    @JoinColumn(name = "idAdmin")
    private Admin admin;
}
