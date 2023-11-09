package pl.bartoszmech.assignexamseats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.exception.NotFound;
import pl.bartoszmech.assignexamseats.exception.StudentNicknameTaken;
import pl.bartoszmech.assignexamseats.mapper.StudentMapper;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.repository.StudentRepository;

import java.util.List;
import java.util.Objects;
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
        doesNicknameExistInSchoolClass(inputStudent.nickname(), inputStudent.schoolClass());
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
        findById(studentId);
        repository.deleteById(studentId);
        LOGGER.info("student deleted");
    }

    public StudentDto edit(Long studentId, StudentDto inputStudent) {
        doesNicknameExistInSchoolClass(inputStudent.nickname(), inputStudent.schoolClass());
        return repository.findById(studentId)
                .map(existingClassroom -> {
                    Optional.ofNullable(inputStudent.nickname()).ifPresent(existingClassroom::setNickname);
                    LOGGER.info("Changes are accepted");
                    Student updatedStudent = repository.save(existingClassroom);
                    return mapper.mapToStudentDto(updatedStudent);
                }).orElseThrow(() -> {
                    LOGGER.error("Invalid student id");
                    return new NotFound("Student with that id does not exist");
                });
    }

    private void findById(long id) {
        repository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Student with id: " + id + "does not exist");
                    return new NotFound("Student with id: " + id + " does not exist");
                });
    }

    private void doesNicknameExistInSchoolClass(String nickname, String schoolClass) {
        if(repository.doesNicknameExistInSchoolClass(schoolClass, nickname)) {
            throw new StudentNicknameTaken("Student with that nickname exists in this class");
        }
    }

    public void checkIfStudentsExist(List<StudentDto> presentStudents) {
        presentStudents.forEach(studentDto -> findById(studentDto.id()));
    }
}
