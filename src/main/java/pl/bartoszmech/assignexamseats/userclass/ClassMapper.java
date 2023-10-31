package pl.bartoszmech.assignexamseats.userclass;

import pl.bartoszmech.assignexamseats.userclass.dto.ClassDto;

import java.util.List;

class ClassMapper {

    UserClass mapToClassroom(ClassDto classDto) {
        return new UserClass(
                classDto.name()
        );
    }

    ClassDto mapToClassroomDto(UserClass userClass) {
        return new ClassDto(userClass.getName());
    }

    List<ClassDto> mapToListDto(List<UserClass> classes) {
        return classes.stream()
                .map(userClass -> new ClassDto(userClass.getName()))
                .toList();
    }
}
