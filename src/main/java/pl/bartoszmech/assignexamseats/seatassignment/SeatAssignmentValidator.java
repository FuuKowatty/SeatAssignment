package pl.bartoszmech.assignexamseats.seatassignment;

import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.failure;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.success;

public class SeatAssignmentValidator {
    List<SeatAssignmentValidationEnum> errors = new LinkedList<>();
    public ValidatorResultFacade validate(int classroomSize, int studentsCount) {
        if(classroomSize < studentsCount) {
            errors.add(SeatAssignmentValidationEnum.TOO_MUCH_STUDENTS);
        }


        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
    }

    private String concatenateValidationMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(Collectors.joining(", "));
    }
}
