package de.fh_dortmund.swt2.backend.dto;

//DTO für eingehende Nachrichten
public class ChatMessage {
    private Long receiverId;
    private String content;

    public Long getReceiverId(){
        return receiverId;
    }

    public void setReceiverId(long receiverId){
        this.receiverId= receiverId;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content= content;
    }
}
