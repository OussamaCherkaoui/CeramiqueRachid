package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.CommandeNotFoundException;
import com.CeramiqueRachid.exception.CommandeProduitNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.model.CommandeProduit;
import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CommandeProduitRepository;
import com.CeramiqueRachid.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommandeProduitService {

    private final CommandeProduitRepository commandeProduitRepository;
    private final CommandeRepository commandeRepository;

    public List<CommandeProduit> getAllCommandesProduitsByCommandeId(Long id) {
        List<CommandeProduit> commandesProduits = commandeProduitRepository.findCommandeProduitsByCommande_Id(id);
        if (commandesProduits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return commandesProduits;
    }

    public CommandeProduit saveCommandeProduit(CommandeProduit commandeProduit) {
        return commandeProduitRepository.save(commandeProduit);
    }

    public CommandeProduit updateCommandeProduit(CommandeProduit commandeProduit) {
        return commandeProduitRepository.save(commandeProduit);
    }

    public CommandeProduit deleteCommandeProduit(Long id) throws CommandeProduitNotFoundException {
        CommandeProduit commandeProduit = commandeProduitRepository.findById(id).orElseThrow(CommandeProduitNotFoundException::new);
        commandeProduitRepository.delete(commandeProduit);
        return commandeProduit;
    }

    public Commande deleteAllCommandeProduitByIdCommande(Long id) throws CommandeNotFoundException {
        Commande commande = commandeRepository.findById(id).orElseThrow(CommandeNotFoundException::new);
        commandeProduitRepository.deleteAllByCommande(commande);
        return commande;
    }

}
