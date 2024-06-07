package alura.com.br.api.service;
import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.course.Status;
import alura.com.br.api.domain.users.Role;
import alura.com.br.api.domain.users.UserRepository;
import alura.com.br.api.domain.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }


    public Course createCourse(Course course) {
        String instructorUsername = course.getInstructor().getUsername();
        Users instructor = userRepository.findByUsername(instructorUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!instructor.getRole().equals(Role.INSTRUCTOR)) {
            throw new RuntimeException("User is not an instructor");
        }

        Optional<Course> existingCourse = courseRepository.findByCode(course.getCode());
        if (existingCourse.isPresent()) {
            throw new RuntimeException("A course with the same code already exists");
        }

        course.setInstructor(instructor);

        return courseRepository.save(course);
    }




    public void deactivateCourse(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setStatus(Status.INACTIVE);
        courseRepository.save(course);
    }

    public Page<Course> listCourses(Pageable pageable, Status status) {
        if (status != null) {
            return courseRepository.findByStatus(status, pageable);
        } else {
            return courseRepository.findAll(pageable);
        }
    }
}


