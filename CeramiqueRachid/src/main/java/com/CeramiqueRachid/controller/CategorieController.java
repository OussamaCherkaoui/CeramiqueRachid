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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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


    @GetMapping("/getCategorieById/{id}")
    public ResponseEntity<?> getCategorieById(@PathVariable("id") Long id) {
        try {
            Categorie categorie = categorieService.getCategorieById(id);
            return ResponseEntity.ok(categorie);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestParam("nom") String nom,
                                  @RequestParam("description") String description,
                                  @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Categorie categorie = new Categorie();
            categorie.setNom(nom);
            categorie.setDescription(description);

            if (imageFile != null && !imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                String uploadDir = System.getProperty("java.io.tmpdir") + "/ceramique/uploads/";
                Files.createDirectories(Paths.get(uploadDir));

                Path filePath = Paths.get(uploadDir + fileName);
                Files.write(filePath, imageFile.getBytes());

                categorie.setImage("/images/" + fileName); // Accessible via le frontend
            }

            Categorie saved = categorieService.saveCategorie(categorie);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
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
