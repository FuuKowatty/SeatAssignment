package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.validator.ValidatorResult;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pl.bartoszmech.assignexamseats.student.StudentValidationEnum.*;
import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.failure;
import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.success;

class StudentValidator {
    public static final Byte MAXIMUM_CHARACTERS = 50;
    public static final Byte MINIMUM_CHARACTERS = 2;
    public static final Byte MINIMUM_AGE = 4;
    public static final Byte MAXIMUM_AGE = 127;

    List<StudentValidationEnum> errors = new LinkedList<>();

    ValidatorResult validate(String firstName, String lastName, Byte age) {
        checkStringValue(firstName, FIRST_NAME_TOO_SHORT, FIRST_NAME_TOO_LONG, FIRST_NAME_DIGITS_NOT_ALLOWED, FIRST_NAME_NULL);
        checkStringValue(lastName, LAST_NAME_TOO_SHORT, LAST_NAME_TOO_LONG, LAST_NAME_DIGITS_NOT_ALLOWED, LAST_NAME_NULL);
        checkAge(age, AGE_NULL, AGE_RANGE_ERROR);


        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
    }

    private void checkAge(Byte age, StudentValidationEnum nullError, StudentValidationEnum rangeError) {
        if(isNull(age)) {
            errors.add(nullError);
            return;
        }
        if(!isInRange(age)) {
            errors.add(rangeError);
        };
    }

    private boolean isInRange(Byte age) {
        return age >= MINIMUM_AGE && age <= MAXIMUM_CHARACTERS;
    }


    private void checkStringValue(String name, StudentValidationEnum tooShortError, StudentValidationEnum tooLongError, StudentValidationEnum digitsError, StudentValidationEnum nullError) {
        if (isNull(name)) {
            errors.add(nullError);
            return;
        }

        if (hasDigits(name)) {
            errors.add(digitsError);
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

    private boolean isStringTooLong(String name) {
        return name.length() > MAXIMUM_CHARACTERS;
    }

    private boolean hasDigits(String name) {
        return Pattern.compile("\\d").matcher(name).find();
    }

    private boolean isStringTooShort(String name) {
        return name.length() < MINIMUM_CHARACTERS;
    }

    private <T> boolean isNull(T value) {
        return value == null;
    }
}
