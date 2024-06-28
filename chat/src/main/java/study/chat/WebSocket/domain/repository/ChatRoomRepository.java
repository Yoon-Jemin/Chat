package study.chat.WebSocket.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.chat.WebSocket.domain.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
