package alura.com.br.api.domain.course;

import alura.com.br.api.domain.users.Users;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "course")
@Entity(name = "Course")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z-]*$")
    private String code;

    @ManyToOne
    @JoinColumn(name = "instructor_username", referencedColumnName = "username")
    private Users instructor;

    @NotBlank
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime creationDate = LocalDateTime.now();

    private LocalDateTime inactivationDate;



}

