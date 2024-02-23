package com.julianjupiter.sis.service;

import com.julianjupiter.sis.dto.StudentDto;
import com.julianjupiter.sis.controller.request.CreateStudentRequest;
import com.julianjupiter.sis.controller.request.UpdateStudentRequest;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDto> findAll();

    Optional<StudentDto> findById(Long id);

    StudentDto save(CreateStudentRequest createStudentRequest);

    StudentDto update(Long id, UpdateStudentRequest updateStudentRequest);

    void delete(Long id);
}
