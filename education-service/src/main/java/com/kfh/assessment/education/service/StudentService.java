package com.kfh.assessment.education.service;


import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.entity.Course;
import com.kfh.assessment.education.entity.Student;
import com.kfh.assessment.education.exception.EmailAlreadyExistsException;
import com.kfh.assessment.education.exception.ResourceNotFoundException;
import com.kfh.assessment.education.mapper.StudentMapper;
import com.kfh.assessment.education.repository.CourseRepository;
import com.kfh.assessment.education.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Optional<Student> student = studentRepository.findByEmail(studentDTO.getEmail());
        if (student.isPresent()) {
            throw new EmailAlreadyExistsException("Student", "Student with Email " + studentDTO.getEmail() + " already exists.");
        }
        studentDTO.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        Student saved = studentRepository.save(mapper.toEntity(studentDTO));
        return mapper.toDTO(saved);
    }
    @Transactional
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return mapper.toDTOList(students);
    }

    @Transactional
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id", id));
        return mapper.toDTO(student);
    }

    @Transactional
    public StudentDTO updateStudentCourses(Long studentId, List<Long> courseIds) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id", studentId));

        Set<Course> courses = courseIds.stream()
                .map(id -> courseRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        courses.forEach(student::enrollInCourse);

        student = studentRepository.save(student);
        return mapper.toDTO(student);

    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Student", "id", id));
        studentRepository.deleteById(id);
    }

}
