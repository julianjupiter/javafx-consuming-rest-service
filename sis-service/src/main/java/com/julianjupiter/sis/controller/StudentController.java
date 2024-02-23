package com.julianjupiter.sis.controller;

import com.julianjupiter.sis.controller.request.CreateStudentRequest;
import com.julianjupiter.sis.controller.request.UpdateStudentRequest;
import com.julianjupiter.sis.controller.response.ApiResponse;
import com.julianjupiter.sis.controller.response.ErrorMessage;
import com.julianjupiter.sis.exception.NotFoundException;
import com.julianjupiter.sis.service.StudentService;
import com.julianjupiter.sis.util.AppUtils;
import com.julianjupiter.sis.util.ErrorCode;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/v1/students")
public class StudentController {
    private final MessageSource messageSource;
    private final StudentService studentService;

    public StudentController(MessageSource messageSource, StudentService studentService) {
        this.messageSource = messageSource;
        this.studentService = studentService;
    }

    @GetMapping
    public ApiResponse findAll() {
        return new ApiResponse(HttpStatus.OK, this.studentService.findAll(), AppUtils.createUri(), OffsetDateTime.now());
    }

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable Long id) {
        return this.studentService.findById(id)
                .map(studentDto -> new ApiResponse(
                        HttpStatus.OK,
                        studentDto,
                        AppUtils.createUri(),
                        OffsetDateTime.now()
                ))
                .orElseThrow(() -> {
                    var params = new Object[]{"Student", id};
                    var errorMessage = new ErrorMessage(ErrorCode.ERR404, this.messageSource.getMessage("not.found", params, Locale.ENGLISH));
                    return new NotFoundException("Not Found Error", List.of(errorMessage));
                });
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody CreateStudentRequest createStudentRequest) {
        var createdStudent = this.studentService.save(createStudentRequest);
        var resourcePath = AppUtils.createUri("/{id}", createdStudent.id());

        return ResponseEntity.created(resourcePath)
                .body(new ApiResponse(HttpStatus.CREATED, createdStudent, resourcePath, OffsetDateTime.now()));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable Long id, @RequestBody UpdateStudentRequest updateStudentRequest) {
        return new ApiResponse(
                HttpStatus.OK,
                this.studentService.update(id, updateStudentRequest),
                AppUtils.createUri(),
                OffsetDateTime.now()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
