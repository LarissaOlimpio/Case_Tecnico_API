package alura.com.br.api.service;
import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.course.Status;
import alura.com.br.api.domain.enrollment.Enrollment;
import alura.com.br.api.domain.enrollment.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnrollUserInCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setStatus(Status.ACTIVE);

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByUserAndCourse(any(), any())).thenReturn(false);
        when(enrollmentRepository.save(any())).thenReturn(enrollment);

        Enrollment result = enrollmentService.enrollUserInCourse(enrollment);

        verify(courseRepository, times(1)).findById(anyLong());
        verify(enrollmentRepository, times(1)).existsByUserAndCourse(any(), any());
        verify(enrollmentRepository, times(1)).save(any());
    }

    @Test
    public void testEnrollUserInCourseWhenCourseNotFound() {
        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(new Course());

        when(courseRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> enrollmentService.enrollUserInCourse(enrollment));
    }

    @Test
    public void testEnrollUserInCourseWhenCourseNotActive() {
        Course course = new Course();
        course.setId(1L);
        course.setStatus(Status.INACTIVE);

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));

        assertThrows(RuntimeException.class, () -> enrollmentService.enrollUserInCourse(enrollment));
    }

    @Test
    public void testEnrollUserInCourseWhenUserAlreadyEnrolled() {
        Course course = new Course();
        course.setId(1L);
        course.setStatus(Status.ACTIVE);

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);

        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(course));
        when(enrollmentRepository.existsByUserAndCourse(any(), any())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> enrollmentService.enrollUserInCourse(enrollment));
    }
}
