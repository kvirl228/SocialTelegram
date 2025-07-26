package org.example.sutod_auth.Servies.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.sutod_auth.Entities.Chat;
import org.example.sutod_auth.Entities.Messange;
import org.example.sutod_auth.Entities.User;
import org.example.sutod_auth.Repositories.ChatRepository;
import org.example.sutod_auth.Repositories.MessangeRepository;
import org.example.sutod_auth.Repositories.UserRepository;
import org.example.sutod_auth.Servies.MessangeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessangeServiceImpl implements MessangeService {

    private final MessangeRepository messangeRepository;

    private final UserRepository userRepository;

    private final ChatRepository chatRepository;

    @Override
    public List<Messange> findAllByChatId(Long chatId) {
        return messangeRepository.findAllByChatId(chatId);
    }

    @Override
    @Transactional
    public Messange sendMessage(Long userId, Long user2Id, String message) {
        Long firstId = Math.min(userId, user2Id);
        Long secondId = Math.max(userId, user2Id);

        Chat chat =  chatRepository.findByTwoId(firstId, secondId).orElseGet(() ->{
            Chat firstChat = new Chat();
            firstChat.setUser(userRepository.findById(firstId).get());
            firstChat.setUser2(userRepository.findById(secondId).get());
            return chatRepository.save(firstChat);
        });

        Messange messange = new Messange();
        messange.setChat(chat);
        messange.setUser(userRepository.findById(firstId).get());
        messange.setText(message);
        messange.setTimestamp(LocalDateTime.now());
        return messangeRepository.save(messange);

    }

    @Override
    public Messange updateMessange(Messange messange, Long messangeId) {
        Optional<Messange> messangeOptional = messangeRepository.findById(messangeId);

        if(messangeOptional.isPresent()) {
            throw new IllegalArgumentException("messange not found" + messangeId);
        }
        return messangeRepository.save(messange);
    }

    @Override
    public void deleteById(Long id) {
        messangeRepository.deleteById(id);
    }
}
