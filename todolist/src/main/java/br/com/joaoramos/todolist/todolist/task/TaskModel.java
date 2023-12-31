package br.com.joaoramos.todolist.todolist.task;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_task")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50)
    private String title;

    private String description;
    private String priority;

    private UUID idUser;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50){
            throw new Exception("O título não pode estar acima de 50 caracteres");
        }
        this.title = title;
    }
}
