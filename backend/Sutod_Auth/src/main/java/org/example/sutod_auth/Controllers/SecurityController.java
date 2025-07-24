package org.example.sutod_auth.Controllers;

import lombok.AllArgsConstructor;
import org.example.sutod_auth.Entities.User;
import org.example.sutod_auth.Entities.UserRequest;
import org.example.sutod_auth.Entities.UserRequestSignIn;
import org.example.sutod_auth.Jwt.JwtCore;
import org.example.sutod_auth.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfigurationSource;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class SecurityController {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtCore jwtCore;

    //регистрация
    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UserRequest userRequest){
        //проверка наличия такого имени в бд
        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already in use");
        }
        //проверка наличия такого имени в email
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
        }

        //Создание юзера
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUsername(userRequest.getUsername());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    //Авторизация
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody UserRequestSignIn userRequest){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword())
            );
            //При введиние верных логинов
//            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        };
        //создание jwt token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generatedToken(authentication);
        return ResponseEntity.ok().body(jwt);
    }
}
