package com.kfh.assessment.education.service;

import com.kfh.assessment.education.dto.CourseDto;
import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.entity.Course;
import com.kfh.assessment.education.entity.Student;
import com.kfh.assessment.education.exception.ResourceNotFoundException;
import com.kfh.assessment.education.mapper.CourseMapper;
import com.kfh.assessment.education.mapper.StudentMapper;
import com.kfh.assessment.education.repository.CourseRepository;
import com.kfh.assessment.education.repository.StudentRepository;
import com.kfh.assessment.education.soap.client.GetCoursesRequest;
import com.kfh.assessment.education.soap.client.GetCoursesResponse;
import com.kfh.assessment.education.soap.SoapClient;
import com.kfh.assessment.education.soap.SoapCourseClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final SoapCourseClient soapCourseClient;
    private final CourseRepository courseRepository;
    private  final StudentRepository studentRepository;
    private final CourseMapper mapper;
    private final StudentMapper studentMapper;
    private final SoapClient soapClient;


    @Transactional
    public List<CourseDto> getCourses() throws IOException {
        GetCoursesRequest request = new GetCoursesRequest();

        GetCoursesResponse response =  soapClient.sendRequest(request);

        System.out.println(response.getCourses().size());

        List<CourseDto> courseDtos = soapCourseClient.getCourses();

        // Save/update to DB
        courseDtos.forEach(dto -> {
            courseRepository.findByCode(dto.getCode())
                    .map(course -> {
                        course.setName(dto.getName());
                        course.setDescription(dto.getDescription());
                        return courseRepository.save(course);
                    })
                    .orElseGet(() -> {
                        var course = new Course();
                        course.setCode(dto.getCode());
                        course.setName(dto.getName());
                        course.setDescription(dto.getDescription());
                        return courseRepository.save(course);
                    });
        });

        List<Course> courses = courseRepository.getAllCourses();
        return mapper.toDTOList(courses);
    }
    @Transactional
    public List<StudentDTO> getCourseStudents(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));
        List<Student> students = courseRepository.getCourseStudents(courseId);
        return studentMapper.toDTOList(students);
    }

    @Transactional
    public List<StudentDTO> getCoursesStudents(List<Long> courseIdList) {
        if (courseIdList != null || !courseIdList.isEmpty()) {
            List<Student> students = courseRepository.getCourseStudents(courseIdList);
            return studentMapper.toDTOList(students);
        }
        return new ArrayList<>();
    }

    @Transactional
    public List<StudentDTO> allocateStudentsToCourse(Long courseId, List<Long> studentIds) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundException("Course", "id", courseId));

        Set<Student> students = studentIds.stream()
                .map(id -> studentRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        students.forEach(student -> student.enrollInCourse(course));
        List<Student> studentList = studentRepository.saveAll(students);
        return studentMapper.toDTOList(studentList);

    }
}