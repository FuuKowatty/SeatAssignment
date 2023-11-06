package pl.bartoszmech.assignexamseats.model.dto;

public record StudentDto(
        Long id,
        String nickname
) {
    public StudentDto(String nickname) {
        this(null, nickname);
    }
}
