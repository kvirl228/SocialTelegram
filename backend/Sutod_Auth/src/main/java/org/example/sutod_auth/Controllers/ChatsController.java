package org.example.sutod_auth.Controllers;

import lombok.AllArgsConstructor;
import org.example.sutod_auth.Entities.Chat;
import org.example.sutod_auth.Servies.Impl.ChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ChatsController {

    private final ChatServiceImpl chatServiceImpl;

    @GetMapping("/chats/{id}")
    public ResponseEntity<List<Chat>> getAllChats(@PathVariable Long id) {
        return ResponseEntity.ok(chatServiceImpl.findAllChatsByUserId(id));
    }

    @GetMapping("/chats/user/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable Long id) {
        return ResponseEntity.ok(chatServiceImpl.findChatById(id));
    }

    @DeleteMapping("/chats/{id}")
    public void deleteChat(@PathVariable Long id) {
        chatServiceImpl.deleteChatById(id);
    }
}
