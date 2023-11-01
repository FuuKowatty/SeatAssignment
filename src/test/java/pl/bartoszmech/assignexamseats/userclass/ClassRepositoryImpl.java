package pl.bartoszmech.assignexamseats.userclass;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClassRepositoryImpl implements ClassRepository{
    Map<String, UserClass> database = new ConcurrentHashMap<>();
    @Override
    public UserClass save(UserClass userClass) {
        return database.put(UUID.randomUUID().toString(), userClass);
    }

    @Override
    public List<UserClass> findAll() {
        return database.values().stream().toList();
    }
}