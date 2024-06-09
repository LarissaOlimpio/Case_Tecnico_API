package alura.com.br.api.service;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.review.CourseReview;
import alura.com.br.api.domain.review.CourseReviewRepository;
import alura.com.br.api.domain.users.Role;
import alura.com.br.api.domain.users.UserRepository;
import alura.com.br.api.domain.users.Users;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseReviewService {
    private final CourseReviewRepository courseReviewRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseReviewService(CourseReviewRepository courseReviewRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.courseReviewRepository = courseReviewRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public CourseReview createReview(CourseReview review) {
        Users user = review.getUser();
        if (!user.getRole().equals(Role.STUDENT)) {
            throw new IllegalArgumentException("Only students can review courses");
        }
        Course course = courseRepository.findById(review.getCourse().getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id " + review.getCourse().getId()));
        if (review.getRating() < 6) {
            Users instructor = course.getInstructor();
            EmailSenderService.send(instructor.getEmail(), "Course Review Alert", "A student gave a rating of " + review.getRating() + " for your course. Feedback: " + review.getFeedback());
        }
        review.setCourse(course);
        return courseReviewRepository.save(review);
    }


    public double calculateNPS(Long courseId) {
        List<CourseReview> reviews = courseReviewRepository.findByCourseId(courseId);
        int promoters = 0;
        int detractors = 0;

        for (CourseReview review : reviews) {
            if (review.getRating() >= 9) {
                promoters++;
            } else if (review.getRating() <= 6) {
                detractors++;
            }
        }

        int totalResponses = reviews.size();
        double nps = (double) (promoters - detractors) / totalResponses * 100;
        return nps;
    }

}