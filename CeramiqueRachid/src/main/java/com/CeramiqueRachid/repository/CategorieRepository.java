package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findAllByOrderByNomDesc();

    Categorie getCategorieById(Long id);
}
