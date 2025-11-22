package com.kfh.assessment.education.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @NotBlank(message = "NameEn is mandatory")
    @Size(max = 100, message = "NameEn must be at most 100 characters")
    public String fullNameEn;

    @NotBlank(message = "NameAr is mandatory")
    @Size(max = 100, message = "NameAr must be at most 100 characters")
    public String fullNameAr;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    public String email;

    @NotBlank(message = "Phone is mandatory")
    @Size(max = 20, message = "Phone number must be at most 20 characters")
    public String phone;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255, message = "Address must be at most 255 characters")
    public String address;

    @NotBlank(message = "Password is mandatory")
    public String password;


}