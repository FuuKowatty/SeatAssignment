package pl.bartoszmech.assignexamseats.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService service;

    public StudentController(StudentService studentService) {
        this.service = studentService;
    }
    @PostMapping("")
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto inputStudent) {
        StudentDto studentDto = service.create(inputStudent);
        return ResponseEntity.ok().body(studentDto);
    }
}
