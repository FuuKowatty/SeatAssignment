package pl.bartoszmech.assignexamseats.userclass;


import static pl.bartoszmech.assignexamseats.userclass.ClassValidator.MAXIMUM_CHARACTERS;
import static pl.bartoszmech.assignexamseats.userclass.ClassValidator.MINIMUM_CHARACTERS;

enum ClassValidationEnum {
    NAME_TOO_SHORT("Classroom name required minimum " + MINIMUM_CHARACTERS + "characters"),
    NAME_TOO_LONG("Classroom name required maximum " + MAXIMUM_CHARACTERS + "characters"),
    NAME_NULL("Classroom name cannot be null");

    final String message;

    ClassValidationEnum(String message) {
        this.message = message;
    }
}
