package pl.bartoszmech.assignexamseats.classroom;

import java.util.List;

public interface ClassroomRepository {
    Classroom save(Classroom classroom);
    List<Classroom> findAll();
}
