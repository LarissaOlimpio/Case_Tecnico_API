package alura.com.br.api.controller;

import alura.com.br.api.domain.course.Course;
import alura.com.br.api.domain.course.CourseRepository;
import alura.com.br.api.domain.course.Status;
import alura.com.br.api.domain.review.CourseReview;
import alura.com.br.api.service.CourseReviewService;
import alura.com.br.api.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseReviewService courseReviewService;

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }


    @PutMapping("/{code}/deactivate")
    public ResponseEntity<Void> deactivateCourse(@PathVariable String code) {
        courseService.deactivateCourse(code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Course>> listCourses(Pageable pageable, @RequestParam(required = false) Status status) {
        Page<Course> courses = courseService.listCourses(pageable, status);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PostMapping("/{courseId}/reviews")
    public ResponseEntity<CourseReview> createReview(@PathVariable Long courseId, @Valid @RequestBody CourseReview review) {
        CourseReview createdReview = courseReviewService.createReview(review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/nps")
    public ResponseEntity<Double> calculateNPS(@PathVariable Long courseId) {
        double nps = courseReviewService.calculateNPS(courseId);
        return new ResponseEntity<>(nps, HttpStatus.OK);
    }
}


