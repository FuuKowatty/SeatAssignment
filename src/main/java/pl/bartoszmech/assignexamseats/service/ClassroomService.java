package pl.bartoszmech.assignexamseats.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.exception.ClassroomNameTaken;
import pl.bartoszmech.assignexamseats.exception.NotFound;
import pl.bartoszmech.assignexamseats.mapper.ClassroomMapper;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.repository.ClassroomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class ClassroomService {
    protected final Logger LOGGER = LoggerFactory.getLogger(ClassroomService.class);
    ClassroomRepository repository;
    ClassroomMapper mapper;

    public ClassroomService(ClassroomRepository repository, ClassroomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public ClassroomDto create(ClassroomDto inputClassroom) {
        checkIfClassroomNameExists(inputClassroom.name());
        try {
            Classroom classroom = repository.save(mapper.mapToClassroom(inputClassroom));
            LOGGER.info("Classroom added with id" + classroom.getId());
            return mapper.mapToClassroomDto(classroom);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Failed to create classroom", e);
            throw new RuntimeException("Failed to create classroom", e);
        }
    }

    public List<ClassroomDto> list() {
        Iterable<Classroom> classroomIterable = repository.findAll();
        List<Classroom> classroomList = StreamSupport.stream(classroomIterable.spliterator(), false)
                .toList();
        return mapper.mapToListDto(classroomList);
    }

    public void deleteById(Long classroomId) {
        findById(classroomId);
        repository.deleteById(classroomId);
        LOGGER.info("student deleted");
    }

    public ClassroomDto edit(Long classroomId, ClassroomDto inputClassroom) {
        checkIfClassroomNameExists(inputClassroom.name());
        return repository.findById(classroomId)
                .map(existingClassroom -> {
                    Optional.ofNullable(inputClassroom.name()).ifPresent(existingClassroom::setName);
                    Optional.ofNullable(inputClassroom.columns()).ifPresent(existingClassroom::setColumns);
                    Optional.ofNullable(inputClassroom.rows()).ifPresent(existingClassroom::setRows);
                    LOGGER.info("Changes are accepted");
                    Classroom updatedClassroom = repository.save(existingClassroom);
                    return mapper.mapToClassroomDto(updatedClassroom);
                }).orElseThrow(() -> {
                    LOGGER.error("Invalid classroom id");
                    return new RuntimeException("");
                });
    }

    public ClassroomDto findById(long id) {
        Classroom classroom = repository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Student with id: " + id + "does not exist");
                    return new NotFound("Classroom with id: " + id + " does not exist");
                });

        return mapper.mapToClassroomDto(classroom);
    }

    private void checkIfClassroomNameExists(String name) {
        if(repository.existsByName(name)) {
            throw new ClassroomNameTaken("Provided classroom name already exists");
        }
    }
}

