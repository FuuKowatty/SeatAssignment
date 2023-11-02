package pl.bartoszmech.assignexamseats.student;

import static pl.bartoszmech.assignexamseats.student.StudentValidator.*;

enum StudentValidationEnum {
    FIRST_NAME_TOO_SHORT("first name requires minimum " + MINIMUM_CHARACTERS + " characters"),
    LAST_NAME_TOO_SHORT("last name requires minimum " + MINIMUM_CHARACTERS + " characters"),
    FIRST_NAME_TOO_LONG("first name requires maximum " + MAXIMUM_CHARACTERS + " characters"),
    LAST_NAME_TOO_LONG("last name requires maximum " + MAXIMUM_CHARACTERS + " characters"),
    FIRST_NAME_DIGITS_NOT_ALLOWED("Digits are not allowed for firstname"),
    LAST_NAME_DIGITS_NOT_ALLOWED("Digits are not allowed for last"),
    FIRST_NAME_NULL("First name cannot be null"),
    LAST_NAME_NULL("Last name cannot be null"),
    AGE_RANGE_ERROR("age should be number between " + MINIMUM_AGE + "-" + MAXIMUM_AGE),
    AGE_NULL("Age cannot be null");

    final String message;
    StudentValidationEnum(String message) {
        this.message = message;
    }
}
