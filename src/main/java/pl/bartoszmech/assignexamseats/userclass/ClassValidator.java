package pl.bartoszmech.assignexamseats.userclass;

import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pl.bartoszmech.assignexamseats.userclass.ClassValidationEnum.*;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.failure;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.success;

class ClassValidator {
    public static final Byte MAXIMUM_CHARACTERS = 50;
    public static final Byte MINIMUM_CHARACTERS = 1;

    List<ClassValidationEnum> errors = new LinkedList<>();

    ValidatorResultFacade validate(String name) {
        checkValue(name, NOT_NULL, TOO_SHORT, TOO_LONG);
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
