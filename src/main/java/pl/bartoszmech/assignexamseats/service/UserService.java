package pl.bartoszmech.assignexamseats.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.bartoszmech.assignexamseats.exception.NotFound;
import pl.bartoszmech.assignexamseats.mapper.UserMapper;
import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.User;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.model.dto.UserDto;
import pl.bartoszmech.assignexamseats.repository.UserRepository;

import java.util.Optional;


@Service
public class UserService {
    protected final Logger LOGGER = LoggerFactory.getLogger(ClassroomService.class);
    UserRepository repository;
    UserMapper mapper;


    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserDto edit(long userId, UserDto userDto) {
        return repository.findById(userId)
                .map(existingUser -> {
                    Optional.ofNullable(userDto.email()).ifPresent(existingUser::setEmail);
                    Optional.ofNullable(userDto.password()).ifPresent(existingUser::setPassword);
                    LOGGER.info("Changes are accepted");
                    User updatedUser = repository.save(existingUser);
                    return mapper.mapToUserDto(updatedUser);
                }).orElseThrow(() -> {
                    LOGGER.error("Invalid classroom id");
                    return new RuntimeException("");
                });
    }

    public void deleteById(long studentId) {
        findById(studentId);
        repository.deleteById(studentId);
        LOGGER.info("student deleted");
    }

    private void findById(long id) {
        repository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("User with id: " + id + "does not exist");
                    return new NotFound("User with id: " + id + " does not exist");
                });
    }
}
