package de.fh_dortmund.swt2.backend.security;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.*;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

@Component
public class AuthChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private ChatAccessProxy chatAccessProxy;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authHeader = accessor.getFirstNativeHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                Authentication auth = chatAccessProxy.verify(token);
                accessor.setUser(auth);


            }
        }
        return message;
    }
}

