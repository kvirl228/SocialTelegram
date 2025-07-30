package org.example.sutod_chats.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.sutod_chats.Entities.DTO.MessageDTO;
import org.example.sutod_chats.Entities.Message;
import org.example.sutod_chats.Servicies.Impl.MessageServiceImpl;
import org.example.sutod_chats.Servicies.Impl.UserClient;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageSocketControllers {

    private final MessageServiceImpl messageService;

    private final SimpMessagingTemplate messagingTemplate;

    private final UserClient  userClient;

    @MessageMapping("/{id}/chat.send")
    public void sendMessage(@Payload Message message, @PathVariable Long id, Principal principal) {

        messageService.sendMessage(message, id);

        String username = userClient.getUsernameById(message.getSenderId());
        String username2 = userClient.getUsernameById(id);

        // Отправить обоим пользователям
        messagingTemplate.convertAndSendToUser(username, "/queue/messages", message);
        messagingTemplate.convertAndSendToUser(username2, "/queue/messages", message);
    }


//    @MessageMapping("/chat.history.{chatId}")
//    @SendToUser("/queue/history")
//    public List<MessageDTO> loadMessages(@DestinationVariable Long chatId) {
//        return messageService.getMessagesByChatId(chatId, page);
//    }

}
