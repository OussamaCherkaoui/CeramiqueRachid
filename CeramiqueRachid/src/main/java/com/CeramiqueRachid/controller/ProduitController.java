package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.service.CommandeService;
import com.CeramiqueRachid.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Produit> produits = produitService.getAllProduits();
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll/{id]")
    public ResponseEntity<?> getAllByCategorieId(@PathVariable("id") Long id) {
        try {
            List<Produit> produits = produitService.getAllProduitsByCategorieId(id);
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll/{name]")
    public ResponseEntity<?> getAllByCategorieNom(@PathVariable("name") String name) {
        try {
            List<Produit> produits = produitService.getAllProduitsByCategorieName(name);
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Produit produit ){
        return ResponseEntity.status(HttpStatus.CREATED).body(produitService.saveProduit(produit));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Produit produit ){
        return ResponseEntity.status(HttpStatus.OK).body(produitService.updateProduit(produit));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Produit deleteProduit = produitService.deleteProduit(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteProduit);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllProductNearingTheEnd")
    public ResponseEntity<?> getAllProductNearingTheEnd() {
        try {
            List<Produit> produits = produitService.getAllProductNearingTheEnd();
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
