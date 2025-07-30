package org.example.sutod_chats.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat", schema = "app_schema", uniqueConstraints = @UniqueConstraint(columnNames = {"c_user_id", "c_user2_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "c_userId")
    private Long userId;

    @Column(name = "c_user2Id")
    private Long userId2;
}
