package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findAllByOrderByNomDesc();

    List<Produit> findAllByCategorie_IdOrderByNomDesc(Long id);
    List<Produit> findAllByCategorie_NomOrderByNomDesc(String name);

    List<Produit> findAllByQuantiteLessThan(Long number);

    List<Produit> getAllByNom(String name);
}
