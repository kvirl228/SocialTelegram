package org.example.sutod_chats.Servicies;

import org.example.sutod_chats.Entities.Chat;

import java.util.List;
import java.util.Optional;

public interface ChatService {

    List<Chat> findChatsByUserId(Long id);

    Optional<Chat> findChatById(Long id);

    Chat createChat(Chat chat);

    Chat updateChat(Chat chat, Long id);

    void deleteById(Long id);
}
