package app.message.service;

import app.message.model.Message;
import app.message.repository.MessageRepository;
import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.CreateNewMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final HttpSession httpSession;
    private final UserService userService;

    public void create(CreateNewMessage createNewMessage) {
        UUID userId = (UUID) httpSession.getAttribute("user_id");
        User sender = userService.getById(userId);
        User receiver = userService.findByUsername(createNewMessage.getReceiver());

        Message message = Message.builder()
                .subject(createNewMessage.getSubject())
                .content(createNewMessage.getContent())
                .sentOn(LocalDateTime.now())
                .sender(sender)
                .receiver(receiver)
                .build();

        this.messageRepository.save(message);
    }

    public List<Message> getAll() {
        return this.messageRepository.findAll();
    }

    public void delete(UUID id) {
        this.messageRepository.deleteById(id);
    }
}
