package study.chat.WebSocket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.chat.WebSocket.domain.User;
import study.chat.WebSocket.domain.repository.UserRepository;
import study.chat.WebSocket.domain.type.Status;

import java.util.List;

import static study.chat.WebSocket.domain.type.Status.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
        user.setStatus(ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user){
        User storedUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if(storedUser != null){
            storedUser.setStatus(OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers(){
        return userRepository.findAllByStatus(ONLINE);
    }
}
