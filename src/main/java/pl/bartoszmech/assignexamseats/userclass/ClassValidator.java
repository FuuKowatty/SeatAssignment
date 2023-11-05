package pl.bartoszmech.assignexamseats.userclass;

import pl.bartoszmech.assignexamseats.validator.ValidatorResult;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.bartoszmech.assignexamseats.userclass.ClassValidationEnum.*;
import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.failure;
import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.success;

class ClassValidator {
    public static final Byte MAXIMUM_CHARACTERS = 50;
    public static final Byte MINIMUM_CHARACTERS = 1;

    List<ClassValidationEnum> errors = new LinkedList<>();

    ValidatorResult validate(String name) {
        checkValue(name, NAME_NULL, NAME_TOO_SHORT, NAME_TOO_LONG);
        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
    }

    private void checkValue(String name, ClassValidationEnum nullError, ClassValidationEnum tooShortError, ClassValidationEnum tooLongError) {
        if (isNull(name)) {
            errors.add(nullError);
            return;
        }
        if (isStringTooShort(name)) {
            errors.add(tooShortError);
        } else if (isStringTooLong(name)) {
            errors.add(tooLongError);
        }
    }

    private String concatenateValidationMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(Collectors.joining(", "));
    }

    private boolean isNull(String value) {
        return value == null;
    }

    private boolean isStringTooLong(String name) {
        return name.length() > MAXIMUM_CHARACTERS;
    }

    private boolean isStringTooShort(String name) {
        return name.length() < MINIMUM_CHARACTERS;
    }

}
