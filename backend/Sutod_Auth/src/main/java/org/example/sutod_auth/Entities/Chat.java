package org.example.sutod_auth.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat", schema = "app_schema", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "user2_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "c_user2_id")
    private User user2;
}
