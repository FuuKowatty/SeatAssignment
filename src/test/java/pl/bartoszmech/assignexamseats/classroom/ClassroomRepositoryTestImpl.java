package pl.bartoszmech.assignexamseats.classroom;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClassroomRepositoryTestImpl implements ClassroomRepository{

    Map<String, Classroom> database = new ConcurrentHashMap<>();

    @Override
    public Classroom save(Classroom classroom) {
        String key = UUID.randomUUID().toString();
        database.put(key, classroom);
        return classroom;
    }
    @Override
    public List<Classroom> findAll() {
        return database.values().stream().toList();
    }
}
