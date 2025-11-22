package com.kfh.assessment.education.repository;

import com.kfh.assessment.education.entity.Course;
import com.kfh.assessment.education.entity.Enrollment;
import com.kfh.assessment.education.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("""
    SELECT c FROM Course c
""")
    List<Course> getAllCourses();
    Optional<Course> findByCode(String code);
    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.enrollments e
    JOIN FETCH e.course c
    WHERE c.id = :courseId
""")
    List<Student> getCourseStudents(Long courseId);

    @Query("""
    SELECT s FROM Student s
    JOIN FETCH s.enrollments e
    JOIN FETCH e.course c
    WHERE c.id IN :courseIdList
""")
    List<Student> getCourseStudents(List<Long> courseIdList);
}