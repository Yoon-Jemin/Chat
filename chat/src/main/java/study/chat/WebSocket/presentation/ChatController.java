package study.chat.WebSocket.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import study.chat.WebSocket.domain.ChatMessage;
import study.chat.WebSocket.dto.ChatNotification;
import study.chat.WebSocket.service.ChatMessageService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ){
        ChatMessage savedMsg = chatMessageService.save(chatMessage);

        // john/queue/messages (john(주인)의 큐에 메시지를 보냄)
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), "/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build()
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipient") String recipientId
    ){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
