package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.model.Commande;
import com.CeramiqueRachid.service.CategorieService;
import com.CeramiqueRachid.service.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/commande")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Commande> commandes = commandeService.getAllCommandes();
            return ResponseEntity.ok(commandes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Commande commande ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commandeService.saveCommande(commande));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Commande commande ){
        return ResponseEntity.status(HttpStatus.OK).body(commandeService.updateCommande(commande));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Commande deleteCommande = commandeService.deleteCommande(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteCommande);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/countOrderDay")
    public ResponseEntity<?> countOrderDay() {
        return new ResponseEntity<>(commandeService.getCountOrderDay(), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getCommandeById(@PathVariable Long id){
        try {
            Commande commande = commandeService.getCommandeById(id);
            return ResponseEntity.ok(commande);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllByDate/{date}")
    public ResponseEntity<?> getAllByDate(@PathVariable LocalDate date) {
        try {
            List<Commande> commandes = commandeService.getAllCommandesByDate(date);
            return ResponseEntity.ok(commandes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllByStatut/{statut}")
    public ResponseEntity<?> getAllByStatut(@PathVariable Boolean statut) {
        try {
            List<Commande> commandes = commandeService.getAllCommandesByStatut(statut);
            return ResponseEntity.ok(commandes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
