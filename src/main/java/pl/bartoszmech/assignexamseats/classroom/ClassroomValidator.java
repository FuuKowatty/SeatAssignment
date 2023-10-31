package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.Errors.*;
import static pl.bartoszmech.assignexamseats.classroom.LayoutValidationResult.failure;
import static pl.bartoszmech.assignexamseats.classroom.LayoutValidationResult.success;
import static pl.bartoszmech.assignexamseats.classroom.ValidationEnum.*;

class ClassroomValidator {
    public static final int MAXIMUM_NUMBER = 99;
    public static final int MINIMUM_NUMBER = 1;

    List<ValidationEnum> errors = new LinkedList<>();

    LayoutValidationResult validate(Integer columns, Integer rows) {
        checkValue(columns, COLUMNS_NULL, COLUMNS_TOO_BIG, COLUMNS_TOO_SMALL);
        checkValue(rows, ROWS_NULL, ROWS_TOO_BIG, ROWS_TOO_SMALL);

        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
    }

    enum Errors {
        NULL,
        TOO_BIG,
        TOO_SMALL,
        NONE
    }

    private void checkValue(Integer value, ValidationEnum nullError, ValidationEnum tooBigError, ValidationEnum tooSmallError) {
        switch (isNull(value) ? NULL : (isGreaterThanMaximum(value) ? TOO_BIG : (isSmallerThanMinimum(value) ? TOO_SMALL : Errors.NONE))) {
            case NULL -> errors.add(nullError);
            case TOO_BIG -> errors.add(tooBigError);
            case TOO_SMALL -> errors.add(tooSmallError);
            case NONE -> {
            }
        }
    }

    private String concatenateValidationMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(Collectors.joining(", "));
    }

    private boolean isNull(Integer value) {
        return value == null;
    }

    private boolean isGreaterThanMaximum(Integer value) {
        return value > MAXIMUM_NUMBER;
    }

    private boolean isSmallerThanMinimum(Integer value) {
        return value < MINIMUM_NUMBER;
    }

}