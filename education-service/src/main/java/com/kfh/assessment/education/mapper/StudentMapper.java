package com.kfh.assessment.education.mapper;

import com.kfh.assessment.education.dto.StudentDTO;
import com.kfh.assessment.education.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTO(Student student);
    Student toEntity(StudentDTO dto);

    List<StudentDTO> toDTOList(List<Student> students);
}