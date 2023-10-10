package br.com.joaoramos.todolist.todolist.user;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    @Column(unique = true)
    private String username;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
