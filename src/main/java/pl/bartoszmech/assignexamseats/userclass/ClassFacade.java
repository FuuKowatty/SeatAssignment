package pl.bartoszmech.assignexamseats.userclass;

import pl.bartoszmech.assignexamseats.userclass.dto.AllClassesDto;
import pl.bartoszmech.assignexamseats.userclass.dto.ClassDto;
import pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade;

import java.util.List;

public class ClassFacade {
    ClassValidator classValidator;
    ClassRepository repository;
    ClassMapper classMapper;

    ClassFacade(ClassValidator classValidator, ClassRepository classRepository, ClassMapper classMapper) {
        this.classValidator = classValidator;
        this.repository = classRepository;
        this.classMapper = classMapper;
    }

    public ClassDto create(ClassDto classDto) {
        String name = classDto.name();
        ValidatorResultFacade validate = classValidator.validate(name);
        if(validate.isValid()) {
            repository.save(new UserClass(name));
        }
        return new ClassDto(validate.resultMessage(), name);
    }

    public AllClassesDto list() {
        List<UserClass> classrooms = repository.findAll();
        List<ClassDto> classroomsDto = classMapper.mapToListDto(classrooms);
        return new AllClassesDto(classroomsDto);
    }
}
