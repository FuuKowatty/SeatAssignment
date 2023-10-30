package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.Map;

public class ClassroomFacade {
    ClassroomValidator classroomValidator;

    ClassroomFacade(ClassroomValidator classroomValidator) {
        this.classroomValidator = classroomValidator;
    }

    public ClassroomDto inputClassroomLayout(Map<String, Integer> classroomLayoutFromUser) {
        LayoutValidationResult validate = classroomValidator.validate(
                classroomLayoutFromUser.get("columns"),
                classroomLayoutFromUser.get("rows")
        );
        return new ClassroomDto(validate.resultMessage());
    }
}
