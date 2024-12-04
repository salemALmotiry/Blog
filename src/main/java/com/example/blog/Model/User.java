package com.example.blog.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor


public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;



    @NotEmpty(message = "Username cannot be null")
    @Size(min = 4 , max = 20 , message = "User name must be between 3 and 30")
    @Pattern(regexp = "^(?=[A-Za-z_])([A-Za-z0-9_.]{4,20})$", message = "Username RegEx Rules:\n" +
            "- Length: 4 to 20 characters.\n" +
            "- Allowed Characters: Letters (both uppercase and lowercase), numbers, underscores (`_`), and periods (`.`).\n" +
            "- Must start with a letter or underscore.\n" +
            "- No spaces or special characters allowed.")

    @Column(columnDefinition = "varchar(20) unique not null")
    private String userName;


    @NotEmpty(message = "User password cannot be null")
    @Size(min = 3 , max = 30 , message = "User password must be between 3 and 30")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,20}$", message = "password RegEx rules: \n" +
            "- Length: between 8 to 20 characters.\n" +
            "- Must contain at least:\n" +
            "   - One uppercase letter (A-Z)\n" +
            "   - One lowercase letter (a-z)\n" +
            "   - One number (0-9)\n" +
            "   - One special character (e.g., `!@#$%^&*`)\n" +
            "- No spaces allowed.")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @NotEmpty(message = "User email cannot be null")
    @Size(min = 10 , max = 50 , message = "User email must be between 10 and 50")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email RegEx rules:"+
            "- Format: Must follow the pattern `local-part@domain`.\n" +
            "- Local part can contain:\n" +
            "  - Letters (A-Z, a-z)\n" +
            "  - Numbers (0-9)\n" +
            "  - Special characters (`!#$%&'*+/=?^_`{|}~-`)\n" +
            "  - Periods (.) but not at the start or end, and not consecutively.\n" +
            "- Domain part must contain:\n" +
            "  - Letters (A-Z, a-z)\n" +
            "  - Numbers (0-9)\n" +
            "  - Periods (.) to separate subdomains.\n" +
            "  - Must end with a valid top-level domain (e.g., `.com`, `.org`, `.net`).")

    @Column(columnDefinition = "varchar(50) unique not null")
    private String email;


    @JsonIgnore
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate = LocalDateTime.now();


}