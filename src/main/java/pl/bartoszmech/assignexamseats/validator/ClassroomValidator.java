package pl.bartoszmech.assignexamseats.validator;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.failure;
import static pl.bartoszmech.assignexamseats.validator.ValidatorResult.success;
import static pl.bartoszmech.assignexamseats.validator.ClassroomValidationError.*;

@Component
public class ClassroomValidator {
    public static final int MAXIMUM_CLASSROOM_LAYOUT_NUMBER = 99;
    public static final int MINIMUM_CLASSROOM_LAYOUT_NUMBER = 1;
    public static final int MINIMUM_CLASSROOM_NAME_CHARACTERS = 2;
    public static final int MAXIMUM_CLASSROOM_NAME_CHARACTER = 50;

    private final List<ClassroomValidationError> errors = new LinkedList<>();

    public ValidatorResult validate(String name, Integer columns, Integer rows) {
        checkNameOfClassroom(name);
        checkClassroomLayoutValue(columns, COLUMNS_NULL, COLUMNS_TOO_BIG, COLUMNS_TOO_SMALL);
        checkClassroomLayoutValue(rows, ROWS_NULL, ROWS_TOO_BIG, ROWS_TOO_SMALL);

        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
    }

    private enum Errors {
        NULL,
        TOO_BIG,
        TOO_SMALL,
        NONE
    }

    private void checkClassroomLayoutValue(Integer value, ClassroomValidationError nullError, ClassroomValidationError tooBigError, ClassroomValidationError tooSmallError) {
        switch (isNull(value) ? Errors.NULL : (isGreaterThanMaximum(value) ? Errors.TOO_BIG : (isSmallerThanMinimum(value) ? Errors.TOO_SMALL : Errors.NONE))) {
            case NULL -> errors.add(nullError);
            case TOO_BIG -> errors.add(tooBigError);
            case TOO_SMALL -> errors.add(tooSmallError);
            case NONE -> {}
        }
    }

    private String concatenateValidationMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(Collectors.joining(", "));
    }

    private void checkNameOfClassroom(String name) {
        if(isNull(name)) {
            errors.add(CLASSROOM_NAME_NULL);
            return;
        }
        if (isTooLong(name)) {
            errors.add(CLASSROOM_NAME_TOO_LONG);
            return;
        }
        if(isTooShort(name)) {
            errors.add(CLASSROOM_NAME_TOO_SHORT);
        }
    }

    private boolean isTooLong(String str) {
        return str.length() >= MAXIMUM_CLASSROOM_NAME_CHARACTER;
    }

    private boolean isTooShort(String str) {
        return str.length() < MINIMUM_CLASSROOM_NAME_CHARACTERS;
    }
    private <T> boolean isNull(T value) {
        return value == null;
    }

    private boolean isGreaterThanMaximum(Integer value) {
        return value > MAXIMUM_CLASSROOM_LAYOUT_NUMBER;
    }

    private boolean isSmallerThanMinimum(Integer value) {
        return value < MINIMUM_CLASSROOM_LAYOUT_NUMBER;
    }

}
