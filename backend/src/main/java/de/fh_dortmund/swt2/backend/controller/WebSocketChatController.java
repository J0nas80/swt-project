package de.fh_dortmund.swt2.backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import de.fh_dortmund.swt2.backend.dto.ChatMessage;
import de.fh_dortmund.swt2.backend.dto.ChatMessageResponse;
import de.fh_dortmund.swt2.backend.service.ChatService;
import jakarta.validation.Valid;
import de.fh_dortmund.swt2.backend.model.Message;


@Controller
public class WebSocketChatController {
@Autowired 
private ChatService chatService;
@Autowired 
private SimpMessagingTemplate messagingTemplate;
@MessageMapping("/chat.send")
public void processMessage(@Valid @Payload ChatMessage msg, Principal user) {
Long senderId = Long.parseLong(user.getName());
Message saved = chatService.sendMessage(senderId, msg.getReceiverId(), msg.getContent());
ChatMessageResponse response = new ChatMessageResponse(
    saved.getId(),
    saved.getContent(),
    saved.getSender().getId(),
    saved.getReceiver().getId(),
    saved.getSentAt()
);

messagingTemplate.convertAndSend("/topic/messages/" + msg.getReceiverId(), response);

}
}
