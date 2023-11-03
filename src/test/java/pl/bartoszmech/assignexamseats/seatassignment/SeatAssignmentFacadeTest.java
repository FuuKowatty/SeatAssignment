package pl.bartoszmech.assignexamseats.seatAssignmentGenerator;

import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto.SeatAssignmentDto;
import pl.bartoszmech.assignexamseats.student.dto.AllStudentsDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;

import java.util.List;


public class SeatAssignmentGeneratorFacadeTest {

    @Test
    public void should_generate_random_seats_assignment_for_each_student_on_generateSeatAssignment() {
        //given
        SeatAssignmentGeneratorFacade generatorFacade = new SeatAssignmentGeneratorFacade();
        ClassroomDto classroom = new ClassroomDto(127, 127);
        AllStudentsDto studentsList = new AllStudentsDto(List.of(
                new StudentDto("Jan", "Kowalski", (byte) 18),
                new StudentDto("Anna", "Antonczak", (byte) 18),
                new StudentDto("Janusz", "Antonczak", (byte) 18),
                new StudentDto("Aleksy", "Marchewka", (byte) 18)
        ));
        //when
        SeatAssignmentDto seats = generatorFacade.generateSeatAssignment(classroom, studentsList);
        System.out.println(seats);
    }
}
