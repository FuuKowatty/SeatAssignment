package pl.bartoszmech.assignexamseats.userclass;

import java.util.List;

public interface ClassRepository {
    UserClass save(UserClass classroom);
    List<UserClass> findAll();
}
