package pl.bartoszmech.assignexamseats.mapper;

import org.springframework.stereotype.Component;
import pl.bartoszmech.assignexamseats.model.Classroom;
import pl.bartoszmech.assignexamseats.model.dto.ClassroomDto;

import java.util.List;

@Component
public class ClassroomMapper {

    public Classroom mapToClassroom(ClassroomDto classroomDto) {
        return new Classroom(
                classroomDto.name(),
                classroomDto.columns(),
                classroomDto.rows()
        );
    }

    public ClassroomDto mapToClassroomDto(Classroom classroom) {
        return new ClassroomDto(classroom.getId(),classroom.getName(),classroom.getColumns() ,classroom.getRows());
    }

    public List<ClassroomDto> mapToListDto(List<Classroom> classrooms) {
        return classrooms.stream()
                .map(this::mapToClassroomDto)
                .toList();
    }
}
