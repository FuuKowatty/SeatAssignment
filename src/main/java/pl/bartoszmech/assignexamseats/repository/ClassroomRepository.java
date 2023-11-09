package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.Classroom;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    boolean existsByName(String name);
}
