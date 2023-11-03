package pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto;

public record SeatDto(
        Integer column,
        Integer row,
        String fullName

) {
}
