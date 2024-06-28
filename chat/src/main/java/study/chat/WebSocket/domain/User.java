package study.chat.WebSocket.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import study.chat.WebSocket.domain.type.Status;

@Getter
@Setter
@Document
public class User {

    @Id
    private String nickName;
    private String fullName;
    private Status status;
}
