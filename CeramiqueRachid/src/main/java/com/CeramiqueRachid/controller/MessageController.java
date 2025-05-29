package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.exception.CategorieNotFoundException;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.ProduitNotFoundException;
import com.CeramiqueRachid.model.Message;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.service.MessageService;
import com.CeramiqueRachid.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Message message ){
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(message));
    }

    @PutMapping("/update/{idMessage}/{idAdmin}")
    public ResponseEntity<?> update(@PathVariable Long idMessage,@PathVariable Long idAdmin ){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.repondueMessage(idMessage,idAdmin));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            Message deleteMessage = messageService.deleteMessage(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteMessage);
        } catch (CategorieNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllMessage() {
        try {
            List<Message> messages = messageService.getAllMessage();
            return ResponseEntity.ok(messages);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllMessageByDate")
    public ResponseEntity<?> getAllMessageByDate(LocalDate date) {
        try {
            List<Message> messages = messageService.getAllMessageByDate(date);
            return ResponseEntity.ok(messages);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/getAllMessageReply")
    public ResponseEntity<?> getAllMessageReply() {
        try {
            List<Message> messages = messageService.getAllMessageReply();
            return ResponseEntity.ok(messages);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllMessageNotReply")
    public ResponseEntity<?> getAllMessageNotReply() {
        try {
            List<Message> messages = messageService.getAllMessageNotReply();
            return ResponseEntity.ok(messages);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/countMessage")
    public ResponseEntity<?> countMessageNotReply() {
        return new ResponseEntity<>(messageService.countMessageNotReply(), HttpStatus.OK);
    }

}
