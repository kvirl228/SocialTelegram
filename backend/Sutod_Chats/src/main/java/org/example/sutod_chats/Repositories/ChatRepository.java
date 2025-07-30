package org.example.sutod_chats.Repositories;

import org.example.sutod_chats.Entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findChatsByUserId(Long id);


}
