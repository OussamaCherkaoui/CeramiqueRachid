package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.repository.AdminRepository;
import com.CeramiqueRachid.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategorieService {

    private final CategorieRepository categorieRepository;
    private final ProduitService produitService;


    public List<Categorie> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAllByOrderByNomDesc();
        if (categories.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return categories;
    }

    public Categorie saveCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie deleteCategorie(Long id) throws CategorieNotFoundException {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(CategorieNotFoundException::new);
        List<Produit> produits = produitService.getAllProduitsByCategorieId(categorie.getId());
        if (!produits.isEmpty()) {
            for (Produit produit : produits) {
                produitService.deleteProduit(produit.getId());
            }
        }
        categorieRepository.delete(categorie);
        return categorie;
    }


    public Categorie getCategorieById(Long id) {
        Categorie categorie = categorieRepository.getCategorieById(id);
        if (categorie.getId()==null) {
            throw new DatabaseEmptyException();
        }
        return categorie;
    }
}
