package org.example.sutod_auth.Repositories;

import org.example.sutod_auth.Entities.Messange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessangeRepository extends JpaRepository<Messange,Long> {
    List<Messange> findAllByChatId(Long chatId);
}
