package com.CeramiqueRachid.service;

import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.model.Message;
import com.CeramiqueRachid.model.Produit;
import com.CeramiqueRachid.repository.MessageRepository;
import com.CeramiqueRachid.repository.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final AdminService adminService;

    public Message saveMessage(Message message) {
        message.setDateEnvoi(LocalDateTime.now());
        message.setEstRepondue(false);
        return messageRepository.save(message);
    }

    public Message repondueMessage(Long idMessage,Long idAdmin) {
        Message message = messageRepository.findById(idMessage).orElseThrow();
        message.setEstRepondue(true);
        message.setAdmin(adminService.getUserById(idAdmin));
        message.setDateReponse(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public Message deleteMessage(Long id) {
        Message message = messageRepository.findById(id).orElseThrow();
        messageRepository.delete(message);
        return message;
    }

    public List<Message> getAllMessage() {
        List<Message> messages = messageRepository.findAllByOrderByDateEnvoiDesc();
        if (messages.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return messages;
    }
    public List<Message> getAllMessageReply() {
        List<Message> messages = messageRepository.findAllByEstRepondueIsTrueOrderByDateEnvoiDesc();
        if (messages.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return messages;
    }

    public List<Message> getAllMessageNotReply() {
        List<Message> messages = messageRepository.findAllByEstRepondueIsFalseOrderByDateEnvoiDesc();
        if (messages.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return messages;
    }


}
