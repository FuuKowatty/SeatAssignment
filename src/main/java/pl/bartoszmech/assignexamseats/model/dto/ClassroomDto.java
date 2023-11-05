package pl.bartoszmech.assignexamseats.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record ClassroomDto(
        @Valid

        @NotBlank(message = "Name cannot be null or empty")
        @Size(min= 2, message = "Name should be longer than 2 characters")
        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,

        @NotNull(message = "Column cannot be null")
        Integer columns,
        @NotNull(message = "Row cannot be null")
        Integer rows
) {
}
