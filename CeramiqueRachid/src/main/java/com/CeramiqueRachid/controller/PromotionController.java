package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.model.Promotion;
import com.CeramiqueRachid.service.ProduitService;
import com.CeramiqueRachid.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PromotionController {

    private final PromotionService promotionService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Promotion> promotions = promotionService.getAllPromotions();
            return ResponseEntity.ok(promotions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Promotion promotion ){
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.savePromotion(promotion));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Promotion promotion  ){
        return ResponseEntity.status(HttpStatus.OK).body(promotionService.updatePromotion(promotion));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Promotion deletePromotion = promotionService.deletePromotion(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletePromotion);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll/{name}")
    public ResponseEntity<?> getAllByProductName(@PathVariable("name") String name) {
        try {
            List<Promotion> promotions = promotionService.getAllPromotionsByProductName(name);
            return ResponseEntity.ok(promotions);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/countPromotionActive")
    public ResponseEntity<?> countPromotionActive() {
        return new ResponseEntity<>(promotionService.countPromotionActive(), HttpStatus.OK);
    }

}
