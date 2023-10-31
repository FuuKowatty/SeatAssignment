package pl.bartoszmech.assignexamseats.userclass;


enum ClassValidationEnum {
    TOO_SHORT("Minimum characters 2"),
    TOO_LONG("Maximum characters 50"),
    NOT_NULL("This field cannot be null");

    final String message;

    ClassValidationEnum(String message) {
        this.message = message;
    }
}
