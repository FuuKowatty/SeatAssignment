package pl.bartoszmech.assignexamseats.mapper;

import org.springframework.stereotype.Component;
import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;


import java.util.List;

@Component
public class StudentMapper {
    public Student mapToStudent(StudentDto classroomDto) {
        return new Student(
                classroomDto.nickname(),
                classroomDto.schoolClass()
        );
    }

    public StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getNickname(),
                student.getSchoolClass()
        );
    }

    public List<StudentDto> mapToListDto(List<Student> students) {
        return students.stream()
                .map(this::mapToStudentDto)
                .toList();
    }
}
