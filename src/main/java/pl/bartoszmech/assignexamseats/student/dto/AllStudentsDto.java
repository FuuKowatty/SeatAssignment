package pl.bartoszmech.assignexamseats.student.dto;

import java.util.List;

public record AllStudentsDto(
        List<StudentDto> students
) {
}
