package pl.bartoszmech.assignexamseats.mapper;

import pl.bartoszmech.assignexamseats.model.Student;
import pl.bartoszmech.assignexamseats.model.User;
import pl.bartoszmech.assignexamseats.model.dto.StudentDto;
import pl.bartoszmech.assignexamseats.model.dto.UserDto;

import java.util.List;

public class UserMapper {
    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.email(),
                userDto.password()
        );
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public List<UserDto> mapToListDto(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
