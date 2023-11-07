package pl.bartoszmech.assignexamseats.model.dto;

public record SeatDto(
            Integer column,
            Integer row,
            String nickname
) {
        public SeatDto(Integer column, Integer row) {
            this(column, row, null);
        }
}
