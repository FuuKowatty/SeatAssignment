package pl.bartoszmech.assignexamseats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.mapper.StudentMapper;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class StudentService {
    protected final Logger LOGGER = LoggerFactory.getLogger(ClassroomService.class);
    StudentRepository repository;
    StudentMapper mapper;

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    public StudentDto create(StudentDto inputStudent) {
        try {
            Student student = repository.save(mapper.mapToStudent(inputStudent));
            LOGGER.info("Student added with id " + student.getId());
            return mapper.mapToStudentDto(student);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error("Failed to create classroom", e);
            throw new RuntimeException("Failed to create classroom", e);
        }
    }

    public List<StudentDto> list() {
        Iterable<Student> studentIterable = repository.findAll();
        List<Student> studentList = StreamSupport.stream(studentIterable.spliterator(), false)
                .toList();
        return mapper.mapToListDto(studentList);
    }

    public void deleteById(long studentId) {
        repository.deleteById(studentId);
    }

    public StudentDto edit(Long studentId, StudentDto studentDto) {
        return repository.findById(studentId)
                .map(existingClassroom -> {
                    Optional.ofNullable(studentDto.nickname()).ifPresent(existingClassroom::setNickname);
                    LOGGER.info("Changes are accepted");
                    Student updatedStudent = repository.save(existingClassroom);
                    return mapper.mapToStudentDto(updatedStudent);
                }).orElseThrow(() -> {
                    LOGGER.error("Invalid classroom id");
                    return new RuntimeException("");
                });
    }
}
