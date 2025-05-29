package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findAllByOrderByDateCommandeDesc();

    Long countCommandeByDateCommande(LocalDate dateCommande);

    List<Commande> findAllByStatutIsFalseOrderByDateCommandeDesc();

    List<Commande> findAllByStatutIsTrueOrderByDateCommandeDesc();

    List<Commande> findAllByDateCommande();
}
