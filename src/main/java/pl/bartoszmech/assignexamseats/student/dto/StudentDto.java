package pl.bartoszmech.assignexamseats.student.dto;

public record StudentDto(
        String message,
        String firstName,
        String lastName,
        Byte age
) {
    public StudentDto(String firstName, String lastName, Byte age) {
        this(null, firstName, lastName, age);
    }
}
