package org.example.sutod_auth.Servies;

import org.example.sutod_auth.Entities.Messange;

import java.util.List;

public interface MessangeService {

    List<Messange> findAllByChatId(Long chatId);

    Messange sendMessage(Long userId, Long user2Id, String message);

    Messange updateMessange(Messange messange, Long messangeId);

    void deleteById(Long id);
}
