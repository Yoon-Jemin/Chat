package study.chat.WebSocket.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.chat.WebSocket.domain.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
}
