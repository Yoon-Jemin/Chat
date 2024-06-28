package study.chat.WebSocket.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import study.chat.WebSocket.domain.User;
import study.chat.WebSocket.domain.type.Status;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findAllByStatus(Status status);
}
