package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.student.dto.StudentDto;

import java.util.Map;

public class StudentFacade {

    public StudentDto addStudent(StudentDto studentInputData) {
        String firstName = studentInputData.firstName();
        String lastName = studentInputData.lastName();
        Byte age = studentInputData.age();

        return studentInputData;
    }

}

//    public ClassroomDto inputClassroomLayout(Map<String, Integer> classroomLayoutFromUser) {
//        Integer columns = classroomLayoutFromUser.get("columns");
//        Integer rows = classroomLayoutFromUser.get("rows");
//        LayoutValidationResult validate = classroomValidator.validate(columns, rows);
//        String message = validate.resultMessage();
//        ClassroomDto classroomDto = new ClassroomDto(message, columns, rows);
//        if(validate.isValid()) {
//            repository.save(new Classroom(columns, rows));
//        }
//        return classroomDto;
//    }