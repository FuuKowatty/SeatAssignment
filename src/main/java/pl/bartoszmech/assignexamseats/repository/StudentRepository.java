package pl.bartoszmech.assignexamseats.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.bartoszmech.assignexamseats.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository  extends CrudRepository<Student, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s " +
            "WHERE s.schoolClass = :schoolClass AND s.nickname = :nickname")
    boolean doesNicknameExistInSchoolClass(
            @Param("schoolClass") String schoolClass,
            @Param("nickname") String nickname
    );
}
