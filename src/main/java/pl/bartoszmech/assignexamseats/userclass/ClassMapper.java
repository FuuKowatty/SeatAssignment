package pl.bartoszmech.assignexamseats.userclass;

import pl.bartoszmech.assignexamseats.userclass.dto.AllClassesDto;
import pl.bartoszmech.assignexamseats.userclass.dto.ClassDto;

import java.util.List;

class ClassMapper {

    UserClass mapToClassroom(ClassDto classDto) {
        return new UserClass(
                classDto.name()
        );
    }

    ClassDto mapToClassroomDto(String message, UserClass userClass) {
        if(message != null) {
            return new ClassDto(message, userClass.getName());
        } else {
            return new ClassDto(userClass.getName());
        }

    }

    List<ClassDto> mapToListDto(List<UserClass> classes) {
        return classes.stream()
                .map(c -> mapToClassroomDto(null, c))
                .toList();
    }
}
