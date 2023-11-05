package pl.bartoszmech.assignexamseats.student;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepositoryImpl implements StudentRepository {
    Map<String, Student> database = new ConcurrentHashMap<>();

    @Override
    public Student save(Student student) {
        database.put(UUID.randomUUID().toString(), student);
        return student;
    }
    @Override
    public List<Student> findAll() {
        return database.values().stream().toList();
    }
}
