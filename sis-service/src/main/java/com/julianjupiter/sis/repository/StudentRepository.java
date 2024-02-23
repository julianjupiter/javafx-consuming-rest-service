package com.julianjupiter.sis.repository;

import com.julianjupiter.sis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
