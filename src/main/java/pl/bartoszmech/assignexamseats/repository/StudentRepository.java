package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.Student;

import java.util.Optional;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {
    Optional<Student> existsByNickname(String nickname);
}
