package pl.bartoszmech.assignexamseats.validator;


import static pl.bartoszmech.assignexamseats.validator.ClassroomValidator.*;

public enum ClassroomValidationError {
    CLASSROOM_NAME_TOO_SHORT("Classroom name is shorter than " + MINIMUM_CLASSROOM_NAME_CHARACTERS),
    CLASSROOM_NAME_TOO_LONG("Classroom name is longer than " + MAXIMUM_CLASSROOM_NAME_CHARACTER),
    CLASSROOM_NAME_NULL("Classroom name is null"),
    COLUMNS_NULL("Columns are null"),
    ROWS_NULL("Rows are null"),
    COLUMNS_TOO_BIG("Columns are bigger than " + MAXIMUM_CLASSROOM_LAYOUT_NUMBER),
    ROWS_TOO_BIG("Rows are bigger than " + MAXIMUM_CLASSROOM_LAYOUT_NUMBER),
    COLUMNS_TOO_SMALL("Columns are smaller than " + MINIMUM_CLASSROOM_LAYOUT_NUMBER),
    ROWS_TOO_SMALL("Rows are smaller than " + MINIMUM_CLASSROOM_LAYOUT_NUMBER);

    public final String message;

    ClassroomValidationError(String message) {
        this.message = message;
    }
}
