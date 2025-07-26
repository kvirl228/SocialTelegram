package org.example.sutod_auth.Servies.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.example.sutod_auth.Entities.Chat;
import org.example.sutod_auth.Entities.Messange;
import org.example.sutod_auth.Repositories.ChatRepository;
import org.example.sutod_auth.Servies.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Override
    public List<Chat> findAllChatsByUserId(Long userId) {
        return chatRepository.findAllById(userId);
    }

    @Override
    public Chat findChatById(Long id) {
        return chatRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteChatById(Long id) {
        chatRepository.deleteById(id);
    }
}
