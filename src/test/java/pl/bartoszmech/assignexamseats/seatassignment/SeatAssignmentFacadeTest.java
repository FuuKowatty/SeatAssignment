package pl.bartoszmech.assignexamseats.seatassignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.seatassignment.dto.SeatAssignmentDto;
import pl.bartoszmech.assignexamseats.student.dto.AllStudentsDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class SeatAssignmentFacadeTest {
    SeatAssignmentFacade seatAssignmentFacade;
    @BeforeEach
    public void initConfiguration() {
        seatAssignmentFacade = new SeatAssignmentConfiguration().seatAssignmentFacadeForTest();
    }

    @Test
    public void should_generate_random_seats_assignment_for_each_student_on_handleSeatAssignment() {
        //given
        ClassroomDto classroom = new ClassroomDto(127, 127);
        AllStudentsDto studentsList = new AllStudentsDto(List.of(
                new StudentDto("Jan", "Kowalski", (byte) 18),
                new StudentDto("Anna", "Antonczak", (byte) 18),
                new StudentDto("Janusz", "Antonczak", (byte) 18),
                new StudentDto("Aleksy", "Marchewka", (byte) 18)
        ));
        //when
        SeatAssignmentDto seats = seatAssignmentFacade.handleSeatAssignment(classroom, studentsList);
        //then
        assertThat(seats).isInstanceOf(SeatAssignmentDto.class);
        assertThat(seats.seats().size()).isEqualTo(studentsList.students().size());
    }
}
