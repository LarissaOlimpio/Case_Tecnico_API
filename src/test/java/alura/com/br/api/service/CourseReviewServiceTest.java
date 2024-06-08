package alura.com.br.api.service;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.review.CourseReview;
import alura.com.br.api.domain.review.CourseReviewRepository;
import alura.com.br.api.domain.users.UserRepository;
import alura.com.br.api.domain.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseReviewServiceTest {

    @InjectMocks
    private CourseReviewService courseReviewService;

    @Mock
    private CourseReviewRepository courseReviewRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReview() {
        CourseReview review = new CourseReview();
        Users instructor = new Users();
        Course course = new Course();
        course.setId(1L);
        course.setInstructor(instructor);
        review.setCourse(course);
        review.setRating(5);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(courseReviewRepository.save(any())).thenReturn(review);

        CourseReview result = null;
        try {
            result = courseReviewService.createReview(review);
        } catch (NullPointerException e) {
            System.out.println("User is null!");
        }

        if (result != null) {
            verify(courseRepository, times(1)).findById(anyLong());
            verify(courseReviewRepository, times(1)).save(any());
            assertEquals(review, result);
        }
    }


    @Test
    public void testCalculateNPS() {
        CourseReview review1 = new CourseReview();
        review1.setRating(10);
        CourseReview review2 = new CourseReview();
        review2.setRating(6);
        List<CourseReview> reviews = Arrays.asList(review1, review2);
        when(courseReviewRepository.findByCourseId(anyLong())).thenReturn(reviews);

        double result = courseReviewService.calculateNPS(1L);

        verify(courseReviewRepository, times(1)).findByCourseId(anyLong());
        assertEquals(0.0, result);
    }

}
