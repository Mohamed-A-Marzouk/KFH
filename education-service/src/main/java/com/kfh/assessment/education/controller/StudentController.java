package com.kfh.assessment.education.controller;

import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return new ResponseEntity<>(service.registerStudent(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return new ResponseEntity<>(service.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = service.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/{id}/courses")
    public ResponseEntity<StudentDTO> updateCourses(@PathVariable Long id, @RequestBody List<Long> courseIds) {
        return ResponseEntity.ok(service.updateStudentCourses(id, courseIds));
    }

    @Operation(summary = "Deletes a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted, no content"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
