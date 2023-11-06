package pl.bartoszmech.assignexamseats.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentDto(
        @Valid

        Long id,

        @NotBlank(message = "Nickname cannot be null or empty")
        @Size(min= 2, message = "Nickname should be longer than 2 characters")
        @Size(max = 50, message = "Nickname cannot be longer than 50 characters")
        String nickname,

        @NotBlank(message = "School class name cannot be null or empty")
        @Size(min= 2, message = "School class name should be longer than 2 characters")
        @Size(max = 50, message = "School class name cannot be longer than 50 characters")
        String schoolClass
) {
    public StudentDto(String nickname, String schoolClass) {
        this(null, nickname, schoolClass);
    }
}
