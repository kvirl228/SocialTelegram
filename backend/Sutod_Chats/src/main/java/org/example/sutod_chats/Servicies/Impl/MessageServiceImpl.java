package org.example.sutod_chats.Servicies.Impl;

import lombok.RequiredArgsConstructor;
import org.example.sutod_chats.Entities.Chat;
import org.example.sutod_chats.Entities.Message;
import org.example.sutod_chats.Repositories.ChatRepository;
import org.example.sutod_chats.Repositories.MessageRepository;
import org.example.sutod_chats.Servicies.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final ChatServiceImpl chatService;

    @Override
    public List<Message> findAllByChatId(Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }

    @Override
    public Message sendMessage(Message message, Long recevierId) {

        if(chatService.findChatById(message.getChatId()).isEmpty()){
            Chat chat = new Chat();
            chat.setId(message.getChatId());
            chat.setUserId2(recevierId);
            chatService.createChat(chat);

            return messageRepository.save(message);
        }

        if(message.getMessage().isEmpty() || message.getSenderId().equals(recevierId) || message.getChatId() == null || message.getDateTime() == null){
            return null;
        }

        return messageRepository.save(message);
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }
}
