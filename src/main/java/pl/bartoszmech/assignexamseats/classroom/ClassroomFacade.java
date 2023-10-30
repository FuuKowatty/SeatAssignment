package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.Map;

public class ClassroomFacade {
    ClassroomValidator classroomValidator;
    ClassroomRepository repository;

    ClassroomFacade(ClassroomValidator classroomValidator, ClassroomRepository classroomRepository) {
        this.classroomValidator = classroomValidator;
        this.repository = classroomRepository;
    }

    public ClassroomDto inputClassroomLayout(Map<String, Integer> classroomLayoutFromUser) {
        Integer columns = classroomLayoutFromUser.get("columns");
        Integer rows = classroomLayoutFromUser.get("rows");
        LayoutValidationResult validate = classroomValidator.validate(columns, rows);
        String message = validate.resultMessage();
        ClassroomDto classroomDto = new ClassroomDto(message, columns, rows);
        if(validate.isValid()) {
            repository.save(new Classroom(columns, rows));
        }
        return classroomDto;
    }
}
