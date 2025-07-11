package de.fh_dortmund.swt2.fake_service.model;

import jakarta.persistence.*; 
import java.util.ArrayList;
import java.util.List;

@Entity
 public class Chat {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    private AppUser participantA;
    @ManyToOne(optional = false)
    private AppUser participantB;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();


    public Chat() {} 

    public Chat(AppUser participantA, AppUser participantB) {
        this.participantA = participantA;
        this.participantB = participantB;
    }

    public Long getId() {
        return id;
    }

    public AppUser getParticipantA() {
        return participantA;
    }

    public void setParticipantA(AppUser participantA) {
        this.participantA = participantA;
    }

    public AppUser getParticipantB() {
        return participantB;
    }

    public void setParticipantB(AppUser participantB) {
        this.participantB = participantB;
    }

    public List<Message> getMessages() {
        return messages;
    }
    public void addMessage(Message message) {
        messages.add(message);
        message.setChat(this); 
    }
    
    public void removeMessage(Message message) {
        messages.remove(message);
        message.setChat(null);
    }

    }
   
