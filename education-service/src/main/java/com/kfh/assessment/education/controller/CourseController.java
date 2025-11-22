package com.kfh.assessment.education.controller;

import com.kfh.assessment.education.dto.CourseDto;
import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCourses() throws IOException {
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> getCourseStudents(@PathVariable Long id) {
        return new ResponseEntity<>(courseService.getCourseStudents(id), HttpStatus.OK);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getCourseStudents(@RequestParam List<Long> studentIdList) {
        return new ResponseEntity<>(courseService.getCoursesStudents(studentIdList), HttpStatus.OK);
    }

    @PostMapping("/{id}/students")
    public ResponseEntity<List<StudentDTO>> updateCourses(@PathVariable Long id, @RequestBody List<Long> studentIds) {
        return ResponseEntity.ok(courseService.allocateStudentsToCourse(id, studentIds));
    }
}
