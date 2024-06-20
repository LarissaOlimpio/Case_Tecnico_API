package alura.com.br.api.service;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.review.CourseReview;
import alura.com.br.api.domain.review.CourseReviewRepository;
import alura.com.br.api.domain.users.Role;
import alura.com.br.api.domain.users.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseReviewServiceTest {

    @InjectMocks
    private CourseReviewService courseReviewService;

    @Mock
    private CourseReviewRepository courseReviewRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReview() {
        CourseReview review = new CourseReview();
        Users user = mock(Users.class);
        when(user.getRole()).thenReturn(Role.STUDENT);
        review.setUser(user);

        Users instructor = mock(Users.class);
        Course course = new Course();
        course.setId(1L);
        course.setInstructor(instructor);
        review.setCourse(course);
        review.setRating(5);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(courseReviewRepository.save(any())).thenReturn(review);

        CourseReview result = courseReviewService.createReview(review);

        verify(courseRepository, times(1)).findById(anyLong());
        verify(courseReviewRepository, times(1)).save(any());
        assertEquals(review, result);
    }

    @Test
    public void testCalculateNPS() {
        when(courseReviewRepository.calculateNPS(anyLong())).thenReturn(50.0);

        double result = courseReviewService.calculateNPS(1L);

        verify(courseReviewRepository, times(1)).calculateNPS(anyLong());
        assertEquals(50.0, result);
    }

}
