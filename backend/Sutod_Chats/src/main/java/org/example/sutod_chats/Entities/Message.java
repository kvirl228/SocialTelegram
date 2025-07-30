package org.example.sutod_chats.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages", schema = "app_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_message")
    private String message;

    @Column(name = "c_senderId")
    private Long senderId;

    @Column(name = "c_chatId")
    private Long chatId;

    @Column(name = "c_dateTime")
    private LocalDateTime dateTime;

}
