package pl.bartoszmech.assignexamseats.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record ClassroomDto(
        @Valid

        Long id,

        @NotBlank(message = "Name cannot be null or empty")
        @Size(min= 2, message = "Name should be longer than 2 characters")
        @Size(max = 50, message = "Name cannot be longer than 50 characters")
        String name,

        @NotNull(message = "Column cannot be null")
        @Min(value = 2, message = "Column should not be smaller than 2")
        @Max(value = 99, message = "Column should not be greater than 99")
        Integer columns,
        @NotNull(message = "Row cannot be null")
        @Min(value = 2, message = "Row should not be smaller than 2")
        @Max(value = 99, message = "Row should not be greater than 99")
        Integer rows
) {
    public ClassroomDto(String name, Integer columns, Integer rows) {
            this(null, name, columns, rows);
    }
}
