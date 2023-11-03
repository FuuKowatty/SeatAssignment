package pl.bartoszmech.assignexamseats.seatAssignmentGenerator;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto.SeatAssignmentDto;
import pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto.SeatDto;
import pl.bartoszmech.assignexamseats.student.dto.AllStudentsDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;
import java.util.Random;


import java.util.*;

public class SeatAssignmentGeneratorFacade {
    public SeatAssignmentDto generateSeatAssignment(ClassroomDto classroomDto, AllStudentsDto presentStudents) {
        //validate
        Integer allSeats = classroomDto.columns() * classroomDto.rows();
        if(presentStudents.students().size() > allSeats) {}

        //generate
        Random rand = new Random();
        int columns = classroomDto.columns();
        Integer rows = classroomDto.rows();
        List<SeatDto> seats = new ArrayList<>();

        List<StudentDto> randomizedStudents = presentStudents.students().stream()
                .map(student -> new AbstractMap.SimpleEntry<>(student, Math.random()))
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .toList();

        int i = 0;
        while (true) {
            String fullName = randomizedStudents.get(i).firstName() + " " + randomizedStudents.get(i).lastName();
            int randomColumn = rand.nextInt(columns)+1;
            int randomRow = rand.nextInt(rows)+1;

            SeatDto randomSeat = new SeatDto(randomColumn, randomRow, fullName);

            if(seats.stream().filter(r -> r.row() == randomRow && r.column() == randomColumn).toList().isEmpty()) {
                seats.add(randomSeat);
            } else {
                continue;
            }

            i++;
            if(seats.size() == randomizedStudents.size()) {
                break;
            }
        }
        return new SeatAssignmentDto(seats);
    }
}
