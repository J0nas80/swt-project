package de.fh_dortmund.swt2.backend.repository;

import de.fh_dortmund.swt2.backend.model.Message;
import de.fh_dortmund.swt2.backend.model.Chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Alle Nachrichten eines Chats, sortiert nach Sendezeit (älteste zuerst)
    List<Message> findByChatOrderBySentAtAsc(Chat chat);
 
}
