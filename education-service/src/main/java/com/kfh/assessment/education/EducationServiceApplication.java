package com.kfh.assessment.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@SpringBootApplication
public class EducationServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EducationServiceApplication.class, args);
	}

}
