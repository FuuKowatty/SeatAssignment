package pl.bartoszmech.assignexamseats.student;

import pl.bartoszmech.assignexamseats.classroom.Classroom;

import java.util.List;

public interface StudentsRepository {
    Student save(Student classroom);
    List<Student> findAll();

}
