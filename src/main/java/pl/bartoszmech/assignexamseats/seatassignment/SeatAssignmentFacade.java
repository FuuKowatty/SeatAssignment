package pl.bartoszmech.assignexamseats.seatassignment;

import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.seatassignment.dto.SeatAssignmentDto;


import java.util.*;

import static java.util.Collections.shuffle;

public class SeatAssignmentFacade {
    SeatAssignmentValidator validator;
    SeatAssignmentGenerator generator;

    public SeatAssignmentFacade(SeatAssignmentValidator validator, SeatAssignmentGenerator generator) {
        this.validator = validator;
        this.generator = generator;
    }

//    public SeatAssignmentDto handleSeatAssignment(ClassroomDto classroomDto, AllStudentsDto presentStudents) {
//        List<StudentDto> students = presentStudents.students();
//        //unhandled validation
//        ValidatorResult validationResult = validator.validate(getClassroomSize(classroomDto), getStudentsCount(students));
//        return generator.generate(
//                classroomDto.columns(),
//                classroomDto.rows(),
//                students
//        );
//    }

//    private int getStudentsCount(List<StudentDto> presentStudents) {
//        return presentStudents.size();
//    }

    private int getClassroomSize(ClassroomDto classroomDto) {
        return classroomDto.rows() * classroomDto.columns();
    }
}
