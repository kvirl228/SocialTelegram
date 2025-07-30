package org.example.sutod_chats.Servicies;

import org.example.sutod_chats.Entities.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAllByChatId(Long chatId);

    Message sendMessage(Message message, Long recevierId);

    void deleteMessageById(Long id);
}
