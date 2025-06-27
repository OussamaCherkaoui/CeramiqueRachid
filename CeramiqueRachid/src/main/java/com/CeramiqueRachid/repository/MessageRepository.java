package com.CeramiqueRachid.repository;

import com.CeramiqueRachid.model.CommandeProduit;
import com.CeramiqueRachid.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByDateEnvoiDesc();

    List<Message> findAllByEstRepondueIsFalseOrderByDateEnvoiDesc();

    List<Message> findAllByEstRepondueIsTrueOrderByDateEnvoiDesc();

    Long countMessageByEstRepondueIsFalse();

    List<Message> findAllByDateEnvoi(LocalDate date);
}
