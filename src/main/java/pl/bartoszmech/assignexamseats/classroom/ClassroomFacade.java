package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.AllClassroomsDto;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomResponseDto;
import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.List;

public class ClassroomFacade {
    ClassroomValidator classroomValidator;
    ClassroomRepository repository;
    ClassroomMapper classroomMapper;

    ClassroomFacade(ClassroomValidator classroomValidator, ClassroomRepository classroomRepository, ClassroomMapper classroomMapper) {
        this.classroomValidator = classroomValidator;
        this.repository = classroomRepository;
        this.classroomMapper = classroomMapper;
    }

    public ClassroomResponseDto addClassroomLayout(ClassroomDto classroomLayoutFromUser) {
        Integer columns = classroomLayoutFromUser.columns();
        Integer rows = classroomLayoutFromUser.rows();
        ValidatorResultFacade validate = classroomValidator.validate(columns, rows);
        String message = validate.resultMessage();
        if(validate.isValid()) {
            repository.save(new Classroom(columns, rows));
        }
        return new ClassroomResponseDto(message, columns, rows);
    }

    public AllClassroomsDto allClasses() {
        List<Classroom> classrooms = repository.findAll();
        List<ClassroomDto> classroomsDto = classroomMapper.mapToListDto(classrooms);
        return new AllClassroomsDto(classroomsDto);
    }
}
