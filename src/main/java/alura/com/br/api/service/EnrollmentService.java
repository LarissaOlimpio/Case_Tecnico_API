package alura.com.br.api.service;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.course.Status;
import alura.com.br.api.domain.enrollment.Enrollment;
import alura.com.br.api.domain.enrollment.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enrollUserInCourse(Enrollment enrollment) {

        Course course = courseRepository.findById(enrollment.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getStatus() != Status.ACTIVE) {
            throw new RuntimeException("Cannot enroll in an inactive course");
        }

        if (enrollmentRepository.existsByUserAndCourse(enrollment.getUser(), course)) {
            throw new RuntimeException("User is already enrolled in this course");
        }

        return enrollmentRepository.save(enrollment);
    }
}
