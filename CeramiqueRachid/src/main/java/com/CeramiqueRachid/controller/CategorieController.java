package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Categorie;
import com.CeramiqueRachid.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/categorie")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategorieController {
    private final CategorieService categorieService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        try {
            List<Categorie> categories = categorieService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }





    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Categorie categorie ){
        return ResponseEntity.status(HttpStatus.CREATED).body(categorieService.saveCategorie(categorie));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Categorie categorie ){
        return ResponseEntity.status(HttpStatus.OK).body(categorieService.updateCategorie(categorie));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Categorie deleteCategorie = categorieService.deleteCategorie(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteCategorie);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
