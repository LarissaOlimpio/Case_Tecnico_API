package alura.com.br.api.domain.enrollment;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserAndCourse(Users user, Course course);
}

