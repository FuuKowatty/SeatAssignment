package pl.bartoszmech.assignexamseats.seatAssignmentGenerator.dto;

import java.util.List;

public record SeatAssignmentDto(
        List<SeatDto> seats
) {
}
