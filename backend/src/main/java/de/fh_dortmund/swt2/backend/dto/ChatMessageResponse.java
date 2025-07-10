package de.fh_dortmund.swt2.backend.dto;

import java.time.LocalDateTime;

//DTO für ausgehende Nachrichten
public class ChatMessageResponse {

    private Long id;
    private String content;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime sentAt;

    public ChatMessageResponse(Long id, String content, Long senderId, Long receiverId, LocalDateTime sentAt) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }
}
