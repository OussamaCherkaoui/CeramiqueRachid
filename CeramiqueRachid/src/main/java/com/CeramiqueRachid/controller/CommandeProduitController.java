package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.model.CommandeProduit;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.service.CommandeProduitService;
import com.CeramiqueRachid.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commandeProduit")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommandeProduitController {

    private final CommandeProduitService commandeProduitService;

    @GetMapping("/getAll/{id}")
    public ResponseEntity<?> getAllCommandesProduitsByCommandeId(@PathVariable("id") Long id) {
        try {
            List<CommandeProduit> commandeProduits = commandeProduitService.getAllCommandesProduitsByCommandeId(id);
            return ResponseEntity.ok(commandeProduits);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CommandeProduit commandeProduit ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeProduitService.saveCommandeProduit(commandeProduit));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CommandeProduit commandeProduit ){
            return ResponseEntity.status(HttpStatus.OK).body(commandeProduitService.updateCommandeProduit(commandeProduit));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            CommandeProduit deleteCommandeProduit = commandeProduitService.deleteCommandeProduit(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteCommandeProduit);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAllByCommandeId(@PathVariable("id") Long id) {
        try {
            Commande commande = commandeProduitService.deleteAllCommandeProduitByIdCommande(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(commande);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
