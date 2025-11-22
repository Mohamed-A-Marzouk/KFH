package com.kfh.assessment.education.mapper;

import com.kfh.assessment.education.dto.CourseDto;
import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.entity.Course;
import com.kfh.assessment.education.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDto toDTO(Course course);
    Course toEntity(CourseDto dto);

    List<CourseDto> toDTOList(List<Course> courses);
}
