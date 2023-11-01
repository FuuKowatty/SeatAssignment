package pl.bartoszmech.assignexamseats.classroom.dto;

import java.util.Optional;

public record ClassroomDto(
        String message,
        Integer columns,
        Integer rows
) {
    public ClassroomDto(Integer columns, Integer rows) {
        this(null, columns, rows);
    }
}
