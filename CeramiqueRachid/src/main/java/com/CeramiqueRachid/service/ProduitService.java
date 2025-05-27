package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        List<Produit> produits = produitRepository.findAllByOrderByNomDesc();
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit deleteProduit(Long id) throws ProduitNotFoundException {
        Produit produit = produitRepository.findById(id).orElseThrow(ProduitNotFoundException::new);
        produitRepository.delete(produit);
        return produit;
    }

}
