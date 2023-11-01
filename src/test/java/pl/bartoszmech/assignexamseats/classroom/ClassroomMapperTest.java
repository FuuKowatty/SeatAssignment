package pl.bartoszmech.assignexamseats.classroom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MAXIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MINIMUM_NUMBER;

public class ClassroomMapperTest {
    ClassroomMapper classroomMapper;
    @BeforeEach
    public void init_mapper() {
        classroomMapper = new ClassroomMapper();
    }

    @Test
    public void should_map_to_classroom() {
        //given
        ClassroomDto classroomDto = new ClassroomDto(MAXIMUM_NUMBER, MINIMUM_NUMBER);
        Classroom expectedClassroom = new Classroom(MAXIMUM_NUMBER, MINIMUM_NUMBER);
        //when
        Classroom classroom = classroomMapper.mapToClassroom(classroomDto);
        //then
        assertThat(classroom).isInstanceOf(Classroom.class);
        assertThat(classroom.getRows()).isEqualTo(expectedClassroom.getRows());
        assertThat(classroom.getColumns()).isEqualTo(expectedClassroom.getColumns());
    }

    @Test
    public void should_map_to_classroomDto() {
        //given
        Classroom classroom = new Classroom(MAXIMUM_NUMBER,MINIMUM_NUMBER);
        ClassroomDto expectedClassroomDto = new ClassroomDto(MAXIMUM_NUMBER,MINIMUM_NUMBER);
        //when
        ClassroomDto classroomDto = classroomMapper.mapToClassroomDto("", classroom);
        //then
        assertThat(classroomDto).isInstanceOf(ClassroomDto.class);
        assertThat(classroomDto.rows()).isEqualTo(expectedClassroomDto.rows());
        assertThat(classroomDto.columns()).isEqualTo(expectedClassroomDto.columns());
    }

    @Test
    public void should_map_to_list_dto() {
        //given
        List<ClassroomDto> expectedListDto = List.of(
                new ClassroomDto(MAXIMUM_NUMBER, MAXIMUM_NUMBER),
                new ClassroomDto(MINIMUM_NUMBER, MINIMUM_NUMBER),
                new ClassroomDto(MAXIMUM_NUMBER, MINIMUM_NUMBER)
        );
        List<Classroom> classroomList = List.of(
                new Classroom(MAXIMUM_NUMBER, MAXIMUM_NUMBER),
                new Classroom(MINIMUM_NUMBER, MINIMUM_NUMBER),
                new Classroom(MAXIMUM_NUMBER, MINIMUM_NUMBER)
        );
        //when
        List<ClassroomDto> classroomListDto = classroomMapper.mapToListDto(classroomList);
        //then
        classroomListDto.stream()
                .map((classroomDto -> assertThat(classroomDto).isInstanceOf(ClassroomDto.class)));
        classroomListDto.stream()
                .map((classroomDto -> assertThat(classroomDto.message()).isEqualTo(null)));
        assertThat(classroomListDto.size()).isEqualTo(expectedListDto.size());

    }

    @Test
    public void should_return_null_message_when_provide_null_as_message() {
        //given
        Classroom classroom = new Classroom(MINIMUM_NUMBER, MINIMUM_NUMBER);
        //when
        ClassroomDto classroomDto = classroomMapper.mapToClassroomDto(null, classroom);
        //then
        assertThat(classroomDto.message()).isNull();
    }
}
