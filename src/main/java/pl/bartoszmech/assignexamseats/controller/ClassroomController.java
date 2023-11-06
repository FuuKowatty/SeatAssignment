package pl.bartoszmech.assignexamseats.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.service.ClassroomService;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService service;

    public ClassroomController(ClassroomService classroomService) {
        this.service = classroomService;
    }

    @PostMapping("")
    public ResponseEntity<ClassroomDto> create(@Valid @RequestBody ClassroomDto inputClassroom) {
        ClassroomDto classroomDto = service.create(inputClassroom);
        return ResponseEntity.ok().body(classroomDto);
    }
}
