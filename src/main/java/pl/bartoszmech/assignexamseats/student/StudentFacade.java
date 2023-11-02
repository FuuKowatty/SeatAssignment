package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.student.dto.StudentDto;
import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.List;

public class StudentFacade {

    StudentValidator studentValidator;
    StudentRepository repository;
    StudentMapper studentMapper;


    StudentFacade(StudentValidator studentValidator, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentValidator = studentValidator;
        this.repository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto create(StudentDto studentInputData) {
        String firstName = studentInputData.firstName();
        String lastName = studentInputData.lastName();
        Byte age = studentInputData.age();

        ValidatorResultFacade validate = studentValidator.validate(firstName, lastName, age);
        String message = validate.resultMessage();
        if(validate.isValid()) {
            repository.save(new Student(firstName, lastName, age));
        }
        return new StudentDto(message, firstName, lastName, age);
    }

    public List<StudentDto> list() {
        return studentMapper.mapToListDto(repository.findAll());
    }
}