package pl.bartoszmech.assignexamseats.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
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
        repository.deleteById(classroomId);
    }

    public void edit(Long classroomId, ClassroomDto classroomDto) {
        repository.findById(classroomId)
                .map(existingClassroom -> {
                    Optional.ofNullable(classroomDto.name()).ifPresent(existingClassroom::setName);
                    Optional.ofNullable(classroomDto.columns()).ifPresent(existingClassroom::setColumns);
                    Optional.ofNullable(classroomDto.rows()).ifPresent(existingClassroom::setRows);
                    LOGGER.info("Changes are accepted");
                    Classroom updatedClassroom = repository.save(existingClassroom);
                    return mapper.mapToClassroomDto(updatedClassroom);
                }).orElseThrow(() -> {
                    LOGGER.error("Invalid classroom id");
                    return new RuntimeException("");
                });
    }
}

