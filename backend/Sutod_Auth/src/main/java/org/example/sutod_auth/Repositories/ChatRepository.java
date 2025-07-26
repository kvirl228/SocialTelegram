package org.example.sutod_auth.Repositories;

import org.apache.logging.log4j.message.Message;
import org.example.sutod_auth.Entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllById(Long userId);

    @Query("SELECT c FROM Chat c WHERE (c.user.id = :user1 AND c.user2.id = :user2) OR (c.user.id = :user2 AND c.user2.id = :user1)")
    Optional<Chat> findByTwoId(@Param("user1") Long user1, @Param("user2") Long user2);

}
