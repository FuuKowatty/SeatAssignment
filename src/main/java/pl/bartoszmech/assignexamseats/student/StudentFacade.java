package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.student.dto.StudentDto;
import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.List;
import java.util.Map;

public class StudentFacade {

    StudentValidator studentValidator;
    StudentsRepository repository;
    StudentMapper studentMapper;


    StudentFacade(StudentValidator studentValidator, StudentsRepository studentsRepository, StudentMapper studentMapper) {
        this.studentValidator = studentValidator;
        this.repository = studentsRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto add(StudentDto studentInputData) {
        String firstName = studentInputData.firstName();
        String lastName = studentInputData.lastName();
        Byte age = studentInputData.age();

        ValidatorResultFacade validate = studentValidator.validate(firstName, lastName, age);
        if(validate.isValid()) {
            repository.save(new Student(firstName, lastName, age));
        }
        return studentInputData;
    }

    public List<StudentDto> list() {
        return studentMapper.mapToListDto(repository.findAll());
    }
}