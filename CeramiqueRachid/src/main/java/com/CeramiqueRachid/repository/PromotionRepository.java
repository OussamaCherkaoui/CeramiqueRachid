package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAllByOrderByTitreDesc();

    List<Promotion> findAllByProduit_NomOrderByTitreDesc(String name);

    @Query(value = "SELECT Count(*) " +
            "FROM Promotion p where current_date between" +
            " p.dateDebut and p.dateFin")
    Long countPromotionActive();

    void deleteAllByProduit_Id(Long produitId);
}
