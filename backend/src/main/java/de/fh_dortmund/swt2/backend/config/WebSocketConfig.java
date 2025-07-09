package de.fh_dortmund.swt2.backend.config;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.messaging.simp.config.*;
import org.springframework.web.socket.config.annotation.*;
import de.fh_dortmund.swt2.backend.security.AuthChannelInterceptor;





@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Autowired
  private AuthChannelInterceptor authChannelInterceptor;

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws-chat").setAllowedOriginPatterns("*").withSockJS();
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/app");
    registry.enableSimpleBroker("/topic");
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(authChannelInterceptor);
  }
}

