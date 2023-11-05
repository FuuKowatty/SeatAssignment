package pl.bartoszmech.assignexamseats.model.dto;

import java.util.Optional;

public record ClassroomDto(
        String name,
        Integer columns,
        Integer rows
) {
}
