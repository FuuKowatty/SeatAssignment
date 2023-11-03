package pl.bartoszmech.assignexamseats.seatAssignmentGenerator;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto.SeatAssignmentDto;
import pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto.SeatDto;
import pl.bartoszmech.assignexamseats.student.dto.AllStudentsDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;
import java.util.Random;


import java.util.*;

import static java.util.Collections.shuffle;

public class SeatAssignmentGeneratorFacade {
    public SeatAssignmentDto generateSeatAssignment(ClassroomDto classroomDto, AllStudentsDto presentStudents) {
        //validate
        int allSeatsCount = classroomDto.columns() * classroomDto.rows();
        if(presentStudents.students().size() > allSeatsCount) {}

        //generate
        int columns = classroomDto.columns();
        int rows = classroomDto.rows();
        List<SeatDto> seats = new ArrayList<>();
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                seats.add(new SeatDto(column, row, ""));
            }
        }
        shuffle(seats);

        List<StudentDto> randomizedStudents = presentStudents.students().stream()
                .sorted(Comparator.comparingDouble(s -> Math.random()))
                .toList();



        List<SeatDto> assignmentedSeats = new LinkedList<>();
        for (int i = 0; i < randomizedStudents.size(); i++) {
            String fullName = randomizedStudents.get(i).firstName() + " " + randomizedStudents.get(i).lastName();
            assignmentedSeats.add(new SeatDto(seats.get(i).column(), seats.get(i).row(), fullName));
        }

        return new SeatAssignmentDto(assignmentedSeats);
    }
}
