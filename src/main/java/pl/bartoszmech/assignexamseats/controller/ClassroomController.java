package pl.bartoszmech.assignexamseats.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.model.dto.SeatDto;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.service.ClassroomService;
import pl.bartoszmech.assignexamseats.service.SeatAssignmentService;
import pl.bartoszmech.assignexamseats.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService service;
    private final SeatAssignmentService seatAssignmentService;
    private final StudentService studentService;

    public ClassroomController(ClassroomService classroomService, SeatAssignmentService seatAssignmentService, StudentService studentService) {
        this.service = classroomService;
        this.seatAssignmentService = seatAssignmentService;
        this.studentService = studentService;
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

    @PostMapping("/{classroomId}/generate-seats")
    public ResponseEntity<List<SeatDto>> generateSeatAssignment(@PathVariable long classroomId, @RequestBody List<StudentDto> presentStudents) {
        ClassroomDto classroomDto = service.findById(classroomId);
        studentService.checkIfStudentsExist(presentStudents);
        return ResponseEntity.ok().body(
                seatAssignmentService.generate(classroomDto.columns(), classroomDto.rows(), presentStudents)
        );
    }
}
