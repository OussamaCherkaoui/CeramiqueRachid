package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.exception.PromotionNotFoundException;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.model.Promotion;
import com.CeramiqueRachid.repository.ProduitRepository;
import com.CeramiqueRachid.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotions() {
        List<Promotion> produits = promotionRepository.findAllByOrderByNomDesc();
        if (produits.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return produits;
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public Promotion deletePromotion(Long id) throws PromotionNotFoundException {
        Promotion promotion = promotionRepository.findById(id).orElseThrow(PromotionNotFoundException::new);
        promotionRepository.delete(promotion);
        return promotion;
    }
}
