package pl.bartoszmech.assignexamseats.userclass.dto;

public record ClassDto(
        String message,
        String name
) {
    public ClassDto(String name) {
        this(null, name);
    }
}
