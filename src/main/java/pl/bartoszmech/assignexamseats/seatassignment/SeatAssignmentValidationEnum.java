package pl.bartoszmech.assignexamseats.seatassignment;

enum SeatAssignmentValidationEnum {
    TOO_MUCH_STUDENTS("Students count is too big for this classroom");

    final String message;
    SeatAssignmentValidationEnum(String message) {
        this.message = message;
    }

}
