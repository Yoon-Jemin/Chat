package study.chat.WebSocket.presentation;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import study.chat.WebSocket.dto.ChatMessage;

@Controller
public class ChatController {

    @MessageMapping("/chat/send-message")
    @SendTo("/topic/public")    // 메시가 해당 URL의 큐로 보내짐
    public ChatMessage sendMesaage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }
    @MessageMapping("/chat/add-user")
    @SendTo("/topic/public")    // 메시가 해당 URL의 큐로 보내짐
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor){

        // WebSocket에 새로운 사용자 추가
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
