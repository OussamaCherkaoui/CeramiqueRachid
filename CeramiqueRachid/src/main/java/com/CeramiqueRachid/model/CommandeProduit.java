package com.CeramiqueRachid.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommandeProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idCommande")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
    @Column
    private Long quantite;
}
