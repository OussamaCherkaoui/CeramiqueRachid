package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.CommandeNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.exception.PromotionNotFoundException;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeProduitService commandeProduitService;

    public List<Commande> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAllByOrderByDateCommandeDesc();
        if (commandes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return commandes;
    }

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande deleteCommande(Long id) throws CommandeNotFoundException {
        Commande commande = commandeRepository.findById(id).orElseThrow(CommandeNotFoundException::new);
        commandeProduitService.deleteAllCommandeProduitByIdCommande(id);
        commandeRepository.delete(commande);
        return commande;
    }

}
