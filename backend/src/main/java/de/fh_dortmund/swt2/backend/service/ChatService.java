package de.fh_dortmund.swt2.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.fh_dortmund.swt2.backend.model.Chat;
import de.fh_dortmund.swt2.backend.model.Message;
import de.fh_dortmund.swt2.backend.model.AppUser;
import de.fh_dortmund.swt2.backend.repository.ChatRepository;
import de.fh_dortmund.swt2.backend.repository.MessageRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import de.fh_dortmund.swt2.backend.repository.AppUserRepository;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AppUserRepository appUserRepository;
    
    public Chat getOrCreateChat(AppUser user1, AppUser user2) {
        return chatRepository.findByParticipants(user1, user2)
                .orElseGet(() -> {
                    Chat chat = new Chat(user1, user2);
                    return chatRepository.save(chat);
                });
    }
    @Transactional
    public Message sendMessage(Long senderId, Long receiverId, String content) {
    AppUser sender = appUserRepository.findById(senderId)
        .orElseThrow(() -> new EntityNotFoundException("Sender nicht gefunden"));
    AppUser receiver = appUserRepository.findById(receiverId)
        .orElseThrow(() -> new EntityNotFoundException("Empfänger nicht gefunden"));
    // Chat zwischen beiden finden oder neu erstellen
    Chat chat = getOrCreateChat(sender, receiver); 

    Message message = new Message();
    message.setSender(sender);
    message.setReceiver(receiver);
    message.setChat(chat);
    message.setContent(content);
    message.setSentAt(LocalDateTime.now());

    return messageRepository.save(message);
    }

    public List<Message> getMessages(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new EntityNotFoundException("Chat nicht gefunden"));
        return messageRepository.findByChatOrderBySentAtAsc(chat);
    }

    public List<Chat> getAllChatsForUser(AppUser user) {
        return chatRepository.findAllByParticipant(user);
    }
}
