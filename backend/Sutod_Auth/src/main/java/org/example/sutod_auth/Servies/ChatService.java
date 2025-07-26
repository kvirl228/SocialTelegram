package org.example.sutod_auth.Servies;

import org.apache.logging.log4j.message.Message;
import org.example.sutod_auth.Entities.Chat;
import org.example.sutod_auth.Entities.Messange;

import java.util.List;

public interface ChatService {

    List<Chat> findAllChatsByUserId(Long userId);

    Chat findChatById(Long id);

    void deleteChatById(Long id);
}
