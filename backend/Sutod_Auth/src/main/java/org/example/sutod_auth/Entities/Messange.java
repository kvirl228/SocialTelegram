package org.example.sutod_auth.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messange", schema = "app_schema")
public class Messange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "c_hat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "c_sender_id")
    private User user;

    @Column(name = "c_text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "c_timestamp")
    private LocalDateTime timestamp;
}
