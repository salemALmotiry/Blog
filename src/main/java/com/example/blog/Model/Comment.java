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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotNull(message = "User ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Post ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer postId;

    @NotEmpty(message = "Comment content cannot be empty")
    @Size(min = 5, max = 500, message = "Comment content must be between 5 and 500 characters")
    @Column(columnDefinition = "TEXT not null")
    private String content;

    @JsonIgnore
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime commentDate = LocalDateTime.now();
}
