package ru.litavrina.ToDoListV2.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:mm")
//    private LocalDateTime localDateTime = LocalDateTime.now();
}
