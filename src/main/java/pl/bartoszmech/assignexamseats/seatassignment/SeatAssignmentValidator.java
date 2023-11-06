package pl.bartoszmech.assignexamseats.seatassignment;


import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class SeatAssignmentValidator {
    List<SeatAssignmentValidationEnum> errors = new LinkedList<>();
//    public ValidatorResult validate(int classroomSize, int studentsCount) {
//        if(classroomSize < studentsCount) {
//            errors.add(SeatAssignmentValidationEnum.TOO_MUCH_STUDENTS);
//        }
//        return errors.isEmpty() ? success() : failure(concatenateValidationMessage());
//    }
    private String concatenateValidationMessage() {
        return errors.stream()
                .map(error -> error.message)
                .collect(Collectors.joining(", "));
    }
}
