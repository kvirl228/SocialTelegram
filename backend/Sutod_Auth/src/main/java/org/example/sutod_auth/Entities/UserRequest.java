package org.example.sutod_auth.Entities;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
//user в который вписываюся данные из request
public class UserRequest {
    String username;
    String email;
    String password;
}
