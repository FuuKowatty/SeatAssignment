package pl.bartoszmech.assignexamseats.seatassignment.dto;

public record SeatDto(
        Integer column,
        Integer row,
        String fullName
) {
    public SeatDto(Integer column, Integer row) {
        this(column, row, null);
    }
}
