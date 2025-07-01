package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.repository.ProduitRepository;
import com.CeramiqueRachid.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProduitService {

    private final ProduitRepository produitRepository;
    private final PromotionRepository promotionRepository;

    public List<Produit> getAllProduits() {
        List<Produit> produits = produitRepository.findAllByOrderByNomDesc();
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public List<Produit> getAllProduitsByCategorieId(Long id) throws CategorieNotFoundException {
        List<Produit> produits = produitRepository.findAllByCategorie_IdOrderByNomDesc(id);
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public Produit saveProduit(Produit produit) {
        produit.setId(null);
        return produitRepository.save(produit);
    }

    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit deleteProduit(Long id) throws ProduitNotFoundException {
        Produit produit = produitRepository.findById(id).orElseThrow(ProduitNotFoundException::new);
        promotionRepository.deleteAllByProduit_Id(produit.getId());
        produitRepository.delete(produit);
        return produit;
    }

    public List<Produit> getAllProduitsByCategorieName(String name) {
        List<Produit> produits = produitRepository.findAllByCategorie_NomOrderByNomDesc(name);
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public List<Produit> getAllProductNearingTheEnd() {
        List<Produit> produits = produitRepository.findAllByQuantiteLessThan(6L);
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public List<Produit> getAllProduitsByName(String name) {
        List<Produit> produits = produitRepository.getAllByNom(name);
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }
}
