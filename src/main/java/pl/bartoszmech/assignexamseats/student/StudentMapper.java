package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.classroom.Classroom;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;

import java.util.List;

public class StudentMapper {
    Student mapToStudent(StudentDto classroomDto) {
        return new Student(
                classroomDto.firstName(),
                classroomDto.lastName(),
                classroomDto.age()
        );
    }

    StudentDto mapToStudentDto(Student student) {
        return new StudentDto(student.getFirstName(), student.getLastName(), student.getAge());
    }

    List<StudentDto> mapToListDto(List<Student> students) {
        return students.stream()
                .map(student -> new StudentDto(student.getFirstName(), student.getLastName(), student.getAge()))
                .toList();
    }
}
