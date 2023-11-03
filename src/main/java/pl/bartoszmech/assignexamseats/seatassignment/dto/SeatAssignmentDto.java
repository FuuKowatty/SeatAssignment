package pl.bartoszmech.assignexamseats.seatassignment.dto;

import java.util.List;

public record SeatAssignmentDto(
        List<SeatDto> seats
) {
}
