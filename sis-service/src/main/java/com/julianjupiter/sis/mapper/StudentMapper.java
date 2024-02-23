package com.julianjupiter.sis.mapper;

import com.julianjupiter.sis.controller.request.CreateStudentRequest;
import com.julianjupiter.sis.controller.request.UpdateStudentRequest;
import com.julianjupiter.sis.dto.StudentDto;
import com.julianjupiter.sis.entity.Student;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class StudentMapper {
    public StudentDto map(Student student) {
        return new StudentDto(
                student.getId(),
                student.getLastName(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getCreatedAt(),
                student.getUpdatedAt()
        );
    }

    public Student map(CreateStudentRequest createStudentRequest) {
        return new Student()
                .setLastName(createStudentRequest.lastName())
                .setFirstName(createStudentRequest.firstName())
                .setMiddleName(createStudentRequest.middleName())
                .setCreatedAt(OffsetDateTime.now());
    }

    public void map(Student student, UpdateStudentRequest updateStudentRequest) {
        student
                .setLastName(updateStudentRequest.lastName())
                .setFirstName(updateStudentRequest.firstName())
                .setMiddleName(updateStudentRequest.middleName())
                .setUpdatedAt(OffsetDateTime.now());
    }
}
