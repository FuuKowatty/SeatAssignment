package pl.bartoszmech.assignexamseats.student;

import static pl.bartoszmech.assignexamseats.student.StudentValidator.MAXIMUM_AGE;
import static pl.bartoszmech.assignexamseats.student.StudentValidator.MINIMUM_AGE;

enum StudentValidationEnum {
    TOO_SHORT("Minimum characters 2"),
    TOO_LONG("Maximum characters 50"),
    DIGITS_NOT_ALLOWED("Digits are not allowed"),
    RANGE_ERROR("age should be number between " + MINIMUM_AGE + "-" + MAXIMUM_AGE),
    NULL_ERROR("This field cannot be null");

    final String message;
    StudentValidationEnum(String message) {
        this.message = message;
    }
}
