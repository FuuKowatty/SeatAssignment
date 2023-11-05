package pl.bartoszmech.assignexamseats.controller;

import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.service.ClassroomService;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    private final ClassroomService service;

    public ClassroomController(ClassroomService classroomService) {
        this.service = classroomService;
    }

    @PostMapping("")
    public ClassroomDto create(@RequestBody ClassroomDto inputClassroom) {
        return service.create(inputClassroom);
    }
}
