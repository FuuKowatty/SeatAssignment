package pl.bartoszmech.assignexamseats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.mapper.ClassroomMapper;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.repository.ClassroomRepository;
import pl.bartoszmech.assignexamseats.validator.ClassroomValidator;
import pl.bartoszmech.assignexamseats.validator.ValidatorResult;


@Service
public class ClassroomService {
    protected final Logger LOGGER = LoggerFactory.getLogger(ClassroomService.class);
    ClassroomRepository repository;
    ClassroomMapper mapper;
    ClassroomValidator validator;

    public ClassroomService(ClassroomRepository repository, ClassroomMapper mapper, ClassroomValidator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator =validator;
    }
    public ClassroomDto create(ClassroomDto inputClassroom) {
       LOGGER.info(inputClassroom.toString());
        ValidatorResult validatorResult = validator.validate(inputClassroom.name(), inputClassroom.columns(), inputClassroom.rows());
        if(!validatorResult.isValid()) {
            throw new RuntimeException(validatorResult.resultMessage());
        }
        try {
            Classroom classroom = repository.save(mapper.mapToClassroom(inputClassroom));
            LOGGER.info("Dish added with id" + classroom.getId());
            return mapper.mapToClassroomDto(classroom);
        } catch (Exception e) {
            LOGGER.error("Failed to create dish", e);
            throw new RuntimeException("Failed to create dish", e);
        }
    }
}

