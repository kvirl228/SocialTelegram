package org.example.sutod_auth.Controllers;

import lombok.AllArgsConstructor;
import org.example.sutod_auth.Entities.User;
import org.example.sutod_auth.Entities.UserResponse.UserAnswer;
import org.example.sutod_auth.Jwt.JwtCore;
import org.example.sutod_auth.Servies.Impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;

    private JwtCore jwtCore;

    @GetMapping("/user")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
        return ResponseEntity.ok().body(authentication.getName()); // вернёт username
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name) {
        User user = userService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        UserAnswer userAnswer = new UserAnswer(user.getUsername(), user.getId());
        return ResponseEntity.ok().body(userAnswer);
    }

    @GetMapping("/id/{jwt}")
    public ResponseEntity<?> getUserByJwt(@PathVariable String jwt) {
        User user = userService.findByName(jwtCore.getNameJwt(jwt)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok().body(user.getId());
    }

}
