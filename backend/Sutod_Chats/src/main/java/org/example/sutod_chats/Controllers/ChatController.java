package org.example.sutod_chats.Controllers;

import lombok.RequiredArgsConstructor;
import org.example.sutod_chats.Entities.Chat;
import org.example.sutod_chats.Servicies.Impl.ChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chats")
@RequiredArgsConstructor
public class ChatController {

    private final ChatServiceImpl chatService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Chat>> getAllChatsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(chatService.findChatsByUserId(id));
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<Optional<Chat>> getChatById(@PathVariable Long id) {
        return ResponseEntity.ok().body(chatService.findChatById(id));
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        if(chat.getUserId()==null || chat.getUserId2()==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(chatService.createChat(chat));
    }
}
