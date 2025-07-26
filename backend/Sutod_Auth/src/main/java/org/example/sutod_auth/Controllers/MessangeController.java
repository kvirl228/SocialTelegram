package org.example.sutod_auth.Controllers;

import lombok.AllArgsConstructor;
import org.example.sutod_auth.Entities.Messange;
import org.example.sutod_auth.Repositories.ChatRepository;
import org.example.sutod_auth.Servies.Impl.MessangeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messange")
@AllArgsConstructor
public class MessangeController {

    private final MessangeServiceImpl messangeServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<List<Messange>> getAll(@PathVariable Long id) {

        return ResponseEntity.ok(messangeServiceImpl.findAllByChatId(id));
    }

    @PostMapping
    public ResponseEntity<Messange> create(@RequestBody Long userId, Long user2Id, String message) {
        return ResponseEntity.ok(messangeServiceImpl.sendMessage(userId, user2Id, message));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Messange> update(@PathVariable Long id, @RequestBody Messange messange) {
        return ResponseEntity.ok(messangeServiceImpl.updateMessange(messange, id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        messangeServiceImpl.deleteById(id);
    }
}
