package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.List;

public interface ClassroomRepository {
    void save(Classroom classroom);
    List<Classroom> findAll();
}
