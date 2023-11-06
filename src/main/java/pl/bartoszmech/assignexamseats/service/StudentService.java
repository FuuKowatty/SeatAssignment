package pl.bartoszmech.assignexamseats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.mapper.StudentMapper;
import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.repository.StudentRepository;

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
}
