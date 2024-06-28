package study.chat.WebSocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.chat.WebSocket.domain.ChatMessage;
import study.chat.WebSocket.domain.repository.ChatMessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        String chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getRecipientId(),
                true
        ).orElseThrow();    // 커스텀 예외 코드
        chatMessage.setChatId(chatId);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        return chatId.map(chatMessageRepository::findByChatId).orElse(new ArrayList<>());
    }
}
