package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
