package pl.bartoszmech.assignexamseats.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    StudentService service;

    public StudentController(StudentService studentService) {
        this.service = studentService;
    }
    @PostMapping("")
    public ResponseEntity<StudentDto> create(@Valid @RequestBody StudentDto inputStudent) {
        return ResponseEntity.ok()
                .body(service.create(inputStudent));
    }

    @GetMapping("")
    public ResponseEntity<List<StudentDto>> list() {
        return ResponseEntity.ok().body(service.list());
    }

    @DeleteMapping("/{studentId}")
    public void delete(@PathVariable long studentId) {
        service.deleteById(studentId);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<StudentDto> update(@PathVariable long studentId, @Valid @RequestBody StudentDto inputStudent) {
        return ResponseEntity.ok().body(service.edit(studentId, inputStudent));
    }
}
