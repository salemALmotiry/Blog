package com.example.blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;



    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    @Column(columnDefinition = "VARCHAR(100) not null")
    private String title;

    @NotEmpty(message = "Content cannot be empty")
    @Size(min = 10, max = 500, message = "Content must be between 10 and 5000 characters")
    @Column(columnDefinition = "TEXT not null")
    private String content;

    @JsonIgnore
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime publishDate = LocalDateTime.now();


    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Category ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;

}
