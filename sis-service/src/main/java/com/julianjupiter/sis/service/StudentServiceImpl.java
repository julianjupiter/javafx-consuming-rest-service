package com.julianjupiter.sis.service;

import com.julianjupiter.sis.controller.request.CreateStudentRequest;
import com.julianjupiter.sis.controller.request.UpdateStudentRequest;
import com.julianjupiter.sis.controller.response.ErrorMessage;
import com.julianjupiter.sis.dto.StudentDto;
import com.julianjupiter.sis.exception.BeanValidationException;
import com.julianjupiter.sis.exception.NotFoundException;
import com.julianjupiter.sis.mapper.StudentMapper;
import com.julianjupiter.sis.repository.StudentRepository;
import com.julianjupiter.sis.util.AppUtils;
import com.julianjupiter.sis.util.ErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
class StudentServiceImpl implements StudentService {
    private final MessageSource messageSource;
    private final Validator validator;
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    StudentServiceImpl(MessageSource messageSource, Validator validator, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.messageSource = messageSource;
        this.validator = validator;
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> findAll() {
        return this.studentRepository.findAll().stream()
                .map(this.studentMapper::map)
                .toList();
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
        return this.studentRepository.findById(id)
                .map(this.studentMapper::map);
    }

    @Override
    public StudentDto save(CreateStudentRequest createStudentRequest) {
        var validationResult = AppUtils.validate(this.validator, createStudentRequest, "createStudentRequest");
        if (validationResult.hasErrors()) {
            List<ErrorMessage> errorMessages = AppUtils.validationErrors(validationResult, this.messageSource);
            throw new BeanValidationException("Bean Validation Error", errorMessages);
        }

        var newStudent = this.studentMapper.map(createStudentRequest);
        var createdStudent = this.studentRepository.save(newStudent);

        return this.studentMapper.map(createdStudent);
    }

    @Override
    public StudentDto update(Long id, UpdateStudentRequest updateStudentRequest) {
        var foundStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> {
                    var params = new Object[]{"Student", id};
                    var errorMessage = new ErrorMessage(ErrorCode.ERR404, this.messageSource.getMessage("not.found", params, Locale.ENGLISH));
                    return new NotFoundException("Not Found Error", List.of(errorMessage));
                });

        var validationResult = AppUtils.validate(this.validator, updateStudentRequest, "updateStudentRequest");
        if (validationResult.hasErrors()) {
            List<ErrorMessage> errorMessages = AppUtils.validationErrors(validationResult, this.messageSource);
            throw new BeanValidationException("Bean Validation Error", errorMessages);
        }

        this.studentMapper.map(foundStudent, updateStudentRequest);
        var updatedStudent = this.studentRepository.save(foundStudent);

        return this.studentMapper.map(updatedStudent);
    }

    @Override
    public void delete(Long id) {
        var foundStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> {
                    var params = new Object[]{"Student", id};
                    var errorMessage = new ErrorMessage(ErrorCode.ERR404, this.messageSource.getMessage("not.found", params, Locale.ENGLISH));
                    return new NotFoundException("Not Found Error", List.of(errorMessage));
                });
        this.studentRepository.delete(foundStudent);
    }
}
