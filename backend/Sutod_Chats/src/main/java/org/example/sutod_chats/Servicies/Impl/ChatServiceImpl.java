package org.example.sutod_chats.Servicies.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.sutod_chats.Entities.Chat;
import org.example.sutod_chats.Repositories.ChatRepository;
import org.example.sutod_chats.Servicies.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public List<Chat> findChatsByUserId(Long id) {
        return chatRepository.findChatsByUserId(id);
    }

    @Override
    public Optional<Chat> findChatById(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public Chat createChat(Chat chat) {
        chat.setId(null);
        return chatRepository.save(chat);
    }

    @Override
    public Chat updateChat(Chat chat, Long id) {
        Optional<Chat> optionalChat = chatRepository.findById(id);
        if(optionalChat.isEmpty()) {
            throw new EntityNotFoundException("Chat with id " + id + " not found");
        }
        chat.setId(id);
        return chatRepository.save(chat);
    }

    @Override
    public void deleteById(Long id) {
        chatRepository.deleteById(id);
    }
}
