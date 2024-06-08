package alura.com.br.api.service;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.course.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CourseServiceTest {

    @InjectMocks
    private CourseService courseService;

    @Mock
    private CourseRepository courseRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeactivateCourse() {
        Course course = new Course();
        course.setCode("code");
        course.setStatus(Status.ACTIVE);

        when(courseRepository.findByCode("code")).thenReturn(Optional.of(course));

        courseService.deactivateCourse("code");

        verify(courseRepository, times(1)).save(course);
        assertEquals(Status.INACTIVE, course.getStatus());
    }

    @Test
    public void testDeactivateCourseNotFound() {
        when(courseRepository.findByCode("code")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            courseService.deactivateCourse("code");
        });
    }

    @Test
    public void testListCourses() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Course> page = mock(Page.class);

        when(courseRepository.findAll(pageable)).thenReturn(page);

        courseService.listCourses(pageable, null);

        verify(courseRepository, times(1)).findAll(pageable);
    }
}
