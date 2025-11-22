package com.kfh.assessment.education.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;
    @NotBlank(message = "Course code is mandatory")
    private String code;
    @NotBlank(message = "Course name is mandatory")
    private String name;
    private String description;
}