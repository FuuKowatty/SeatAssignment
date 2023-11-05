package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}
