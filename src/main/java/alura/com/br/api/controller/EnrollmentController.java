package alura.com.br.api.controller;

import alura.com.br.api.domain.enrollment.Enrollment;
import alura.com.br.api.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping
    public ResponseEntity<Enrollment> enrollUserInCourse(@RequestBody Enrollment enrollment) {
        Enrollment createdEnrollment = enrollmentService.enrollUserInCourse(enrollment);
        return new ResponseEntity<>(createdEnrollment, HttpStatus.CREATED);
    }
}
