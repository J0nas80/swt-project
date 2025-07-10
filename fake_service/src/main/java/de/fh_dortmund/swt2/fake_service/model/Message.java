package de.fh_dortmund.swt2.fake_service.model;

import jakarta.persistence.*; 
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

 @Entity
 public class Message {
  @Id @GeneratedValue private Long id;
  @ManyToOne(optional = false)
  private AppUser sender;
  @ManyToOne(optional = false)
  private AppUser receiver; 
  @ManyToOne(optional = false)
  private Chat chat;
  @Column(nullable = false)
  private String content;
  private LocalDateTime sentAt;

  public Message() {}

  public Message(AppUser sender, AppUser receiver, Chat chat, String content) {
        this.sender = sender;
        this.receiver=receiver;
        this.chat = chat;
        this.content = content;
        this.sentAt = LocalDateTime.now();
    }  


    public Long getId() {
        return id;
    }

    public AppUser getSender() {
        return sender;
    }

    public void setSender(AppUser sender) {
        this.sender = sender;
    }

     public AppUser getReceiver() {
        return receiver;
    }

    public void setReceiver(AppUser receiver) {
        this.receiver = receiver;
    }
    
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    }
