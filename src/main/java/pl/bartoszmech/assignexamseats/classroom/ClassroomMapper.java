package pl.bartoszmech.assignexamseats.classroom;

import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.List;

class ClassroomMapper {

    Classroom mapToClassroom(ClassroomDto classroomDto) {
        return new Classroom(
                classroomDto.columns(),
                classroomDto.rows()
        );
    }

    ClassroomDto mapToClassroomDto(Classroom classroom) {
        return new ClassroomDto(classroom.getColumns(), classroom.getRows());
    }

    List<ClassroomDto> mapToListDto(List<Classroom> classrooms) {
        return classrooms.stream()
                .map(classroom -> new ClassroomDto(classroom.getColumns(), classroom.getRows()))
                .toList();
    }
}
