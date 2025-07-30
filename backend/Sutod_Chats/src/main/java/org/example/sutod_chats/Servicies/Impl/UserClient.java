package org.example.sutod_chats.Servicies.Impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.sutod_chats.Entities.DTO.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    public String getUsernameById(Long userId) {
        ResponseEntity<UserDTO> response = restTemplate.getForEntity("http://loacalhost:8080/api/users/user" + userId, UserDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            assert response.getBody() != null;
            return response.getBody().getUsername();
        }

        throw new RuntimeException("User not found");
    }
}