package pl.bartoszmech.assignexamseats.model.dto;

public record UserDto(
        Long id,
        String email,
        String password
) {
}
