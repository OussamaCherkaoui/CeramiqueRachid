package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.model.CommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeProduitRepository  extends JpaRepository<CommandeProduit, Long> {
    List<CommandeProduit> findCommandeProduitsByCommande_Id(Long id);

    void deleteAllByCommande(Commande commande);
}
