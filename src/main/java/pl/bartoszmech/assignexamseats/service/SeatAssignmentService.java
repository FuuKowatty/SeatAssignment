package pl.bartoszmech.assignexamseats.service;

import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.model.dto.SeatDto;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.shuffle;

@Service
public class SeatAssignmentService {
    public List<SeatDto> generate(int columns, int rows, List<StudentDto> students) {
        List<SeatDto> seats = generateSeats(columns, rows);
        shuffle(seats);

        List<StudentDto> randomizedStudents = randomizeOrderOfStudents(students);

        return assignSeats(randomizedStudents, seats);
    }

    private List<SeatDto> generateSeats(int columns, int rows) {
        List<SeatDto> seats = new LinkedList<>();
        for (int row = 1; row <= rows; row++) {
            for (int column = 1; column <= columns; column++) {
                seats.add(new SeatDto(column, row));
            }
        }
        return seats;
    }

    private List<StudentDto> randomizeOrderOfStudents(List<StudentDto> students) {
        return students.stream()
                .sorted(Comparator.comparingDouble(s -> Math.random()))
                .toList();
    }

    private List<SeatDto> assignSeats(List<StudentDto> students, List<SeatDto> seats) {
        List<SeatDto> assignedSeats = new LinkedList<>();
        for (int i = 0; i < students.size(); i++) {
            assignedSeats.add(
                    new SeatDto(
                        seats.get(i).column(),
                        seats.get(i).row(),
                        (students.get(i).nickname())
                    )
            );
        }
        return assignedSeats;
    }
}
