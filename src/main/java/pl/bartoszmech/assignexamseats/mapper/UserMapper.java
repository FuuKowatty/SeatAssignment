package pl.bartoszmech.assignexamseats.mapper;

import org.springframework.stereotype.Component;
import pl.bartoszmech.assignexamseats.model.User;
import pl.bartoszmech.assignexamseats.model.dto.UserDto;

import java.util.List;

@Component
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
