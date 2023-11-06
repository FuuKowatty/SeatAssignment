package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.Student;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {

}
