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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping("/getAllByCategorieId/{id}")
    public ResponseEntity<?> getAllByCategorieId(@PathVariable("id") Long id) {
        try {
            List<Produit> produits = produitService.getAllProduitsByCategorieId(id);
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllByCategorieName/{name}")
    public ResponseEntity<?> getAllByCategorieName(@PathVariable("name") String name) {
        try {
            List<Produit> produits = produitService.getAllProduitsByCategorieName(name);
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllByName/{name}")
    public ResponseEntity<?> getAllByName(@PathVariable("name") String name) {
        try {
            List<Produit> produits = produitService.getAllProduitsByName(name);
            return ResponseEntity.ok(produits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("nom") String nom,
                                  @RequestParam("description") String description,
                                  @RequestParam("prix") String prix,
                                  @RequestParam("quantite") Long quantite,
                                  @RequestParam("categorieId") Long categorieId,
                                  @RequestParam(value = "image", required = false) MultipartFile imageFile){
        try {
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setPrix(prix);
            produit.setQuantite(quantite);

            // Trouver la catégorie
            Categorie cat = new Categorie();
            cat.setId(categorieId);
            produit.setCategorie(cat);

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String uploadDir = System.getProperty("java.io.tmpdir") + "/ceramique/uploads/";
                Files.createDirectories(Paths.get(uploadDir));

                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());

                produit.setImage("/images/" + fileName);
            }

            Produit saved = produitService.saveProduit(produit);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur: " + e.getMessage());
        }
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
