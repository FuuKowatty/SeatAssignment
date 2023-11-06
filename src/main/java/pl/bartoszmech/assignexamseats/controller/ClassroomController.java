package pl.bartoszmech.assignexamseats.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.service.ClassroomService;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<List<ClassroomDto>> list() {
        return ResponseEntity.ok().body(service.list());
    }

    @DeleteMapping("/{classroomId}")
    public void delete(@PathVariable long classroomId) {
        service.deleteById(classroomId);
    }

    @PatchMapping("/{classroomId}")
    public ResponseEntity<ClassroomDto> update(@PathVariable long classroomId, @Valid @RequestBody ClassroomDto inputClassroom) {
        return ResponseEntity.ok().body(service.edit(classroomId, inputClassroom));
    }
}
