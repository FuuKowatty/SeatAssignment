package pl.bartoszmech.assignexamseats.student;

import java.util.List;

public interface StudentRepository {
    Student save(Student classroom);
    List<Student> findAll();

}
