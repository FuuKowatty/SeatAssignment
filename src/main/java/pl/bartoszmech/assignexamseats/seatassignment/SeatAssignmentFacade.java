package pl.bartoszmech.assignexamseats.seatassignment;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.seatassignment.dto.SeatAssignmentDto;
import pl.bartoszmech.assignexamseats.seatassignment.dto.SeatDto;
import pl.bartoszmech.assignexamseats.student.dto.AllStudentsDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;
import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;


import java.util.*;

import static java.util.Collections.shuffle;

public class SeatAssignmentFacade {
    SeatAssignmentValidator validator;
    SeatAssignmentGenerator generator;

    public SeatAssignmentFacade(SeatAssignmentValidator validator) {
        this.validator = validator;
    }

    public SeatAssignmentDto handleSeatAssignment(ClassroomDto classroomDto, AllStudentsDto presentStudents) {
        List<StudentDto> students = presentStudents.students();
        //unhandled validation
        ValidatorResultFacade validationResult = validator.validate(getClassroomSize(classroomDto), getStudentsCount(students));
        return generator.generate(
                classroomDto.columns(),
                classroomDto.rows(),
                students
        );
    }

    private int getStudentsCount(List<StudentDto> presentStudents) {
        return presentStudents.size();
    }

    private int getClassroomSize(ClassroomDto classroomDto) {
        return classroomDto.rows() * classroomDto.columns();
    }
}
