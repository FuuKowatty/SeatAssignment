package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.List;
import java.util.Optional;

class ClassroomMapper {

    Classroom mapToClassroom(ClassroomDto classroomDto) {
        return new Classroom(
                classroomDto.columns(),
                classroomDto.rows()
        );
    }

    ClassroomDto mapToClassroomDto(String message, Classroom classroom) {
        if(message != null) {
            return new ClassroomDto(message, classroom.getColumns(), classroom.getRows());
        } else {
            return new ClassroomDto(classroom.getColumns(), classroom.getRows());
        }

    }

    List<ClassroomDto> mapToListDto(List<Classroom> classrooms) {
        return classrooms.stream()
                .map(classroom -> mapToClassroomDto(null, classroom))
                .toList();
    }
}
