package alura.com.br.api.domain.review;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.users.Users;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "course_review")
@Entity(name = "Course_review")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CourseReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private int rating;
    private String feedback;

}

