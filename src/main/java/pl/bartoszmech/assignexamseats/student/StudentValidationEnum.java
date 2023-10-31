package pl.bartoszmech.assignexamseats.student;

enum StudentValidationEnum {
    TOO_SHORT("Minimum characters 2"),
    TOO_LONG("Maximum characters 50"),
    DIGITS_NOT_ALLOWED("Digits are not allowed"),
    NOT_NULL("This field cannot be null");

    final String message;
    StudentValidationEnum(String message) {
        this.message = message;
    }
}
