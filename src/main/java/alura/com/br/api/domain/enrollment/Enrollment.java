package alura.com.br.api.domain.enrollment;
import java.time.LocalDateTime;
import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "enrollment")
@Entity(name = "Enrollment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    private LocalDateTime enrollmentDate = LocalDateTime.now();



}


