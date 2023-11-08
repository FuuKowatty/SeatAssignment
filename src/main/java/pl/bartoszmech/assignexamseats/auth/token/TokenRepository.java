package pl.bartoszmech.assignexamseats.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.bartoszmech.assignexamseats.model.User;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Modifying
    @Query(value = """
  delete from Token t where t.user.id = :id
  """)
    Optional<Token> deleteTokenByUserId(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query(value = """
    delete from Token t where t = :token
    """)
    void deleteToken(@Param("token") Token token);
    Optional<Token> findByToken(String token);
    Optional<Token> findByUser(User user);

}
