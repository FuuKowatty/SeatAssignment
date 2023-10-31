package pl.bartoszmech.assignexamseats.classroom;

import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MAXIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MINIMUM_NUMBER;

enum ValidationEnum {
    COLUMNS_NULL("Columns are null"),
    ROWS_NULL("Rows are null"),
    COLUMNS_TOO_BIG("Columns are bigger than " + MAXIMUM_NUMBER),
    ROWS_TOO_BIG("Rows are bigger than " + MAXIMUM_NUMBER),
    COLUMNS_TOO_SMALL("Columns are smaller than " + MINIMUM_NUMBER),
    ROWS_TOO_SMALL("Rows are smaller than " + MINIMUM_NUMBER);

    final String message;

    ValidationEnum(String message) {
        this.message = message;
    }
}
