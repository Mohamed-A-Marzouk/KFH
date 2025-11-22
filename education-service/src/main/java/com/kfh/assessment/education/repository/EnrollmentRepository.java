package com.kfh.assessment.education.repository;

import com.kfh.assessment.education.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {}