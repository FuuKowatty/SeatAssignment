package pl.bartoszmech.assignexamseats.classroom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.classroom.dto.AllClassroomsDto;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.userclass.dto.ClassDto;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MAXIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MINIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.SUCCESS_MESSAGE;

public class ClassroomFacadeTest {

    ClassroomFacade classroomFacade;
    ClassroomRepository repository = new ClassroomRepositoryTestImpl();
    @BeforeEach
    public void initClassroomFacade() {
        classroomFacade = new ClassroomConfigurator().classroomFacadeForTest(repository);
    }

    @Test
    public void should_return_dto_with_success_message_when_provide_valid_columns_and_rows() {
        //given
        Integer columns = MAXIMUM_NUMBER;
        Integer rows = MAXIMUM_NUMBER;
        ClassroomDto classLayoutFromUser = new ClassroomDto(columns, rows);
        ClassroomDto expectedDto = new ClassroomDto(SUCCESS_MESSAGE, columns, rows);
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedDto.message());
        assertThat(classroomResponseDto).isInstanceOf(ClassroomDto.class);
    }

    @Test
    public void should_return_column_too_big_error_when_provide_too_big_column() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER+1, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_TOO_BIG.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_too_small_error_when_provide_too_small_column() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MINIMUM_NUMBER-1, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_TOO_SMALL.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_null_error_when_provide_column_null() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(null, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_NULL.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_big_error_when_provide_too_big_row() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, MAXIMUM_NUMBER+1);

        String expectedMessage = ClassroomValidationEnum.ROWS_TOO_BIG.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_small_error_when_provide_too_small_row() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, MINIMUM_NUMBER-1);
        String expectedMessage = ClassroomValidationEnum.ROWS_TOO_SMALL.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_null_error_when_provide_row_null() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, null);
        String expectedMessage = ClassroomValidationEnum.ROWS_NULL.message;
        //when
        ClassroomDto classroomResponseDto = classroomFacade.create(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_all_classrooms_when_list() {
        //given
        Classroom classroom1 = repository.save(new Classroom(MAXIMUM_NUMBER, MINIMUM_NUMBER));
        List<ClassroomDto> expectedDto = List.of(new ClassroomDto(MAXIMUM_NUMBER, MAXIMUM_NUMBER));
        //when
        AllClassroomsDto classroomsDto = classroomFacade.list();
        //then

        assertThat(classroomsDto.classroomsDto().size()).isEqualTo(expectedDto.size());
        assertThat(classroomsDto.classroomsDto().get(0).columns()).isEqualTo(expectedDto.get(0).columns());
        assertThat(classroomsDto.classroomsDto().get(0)).isInstanceOf(ClassroomDto.class);
    }

    @Test
    public void should_return_list_with_null_messages_when_list() {
        //given
        Classroom classroom1 = repository.save(new Classroom(MAXIMUM_NUMBER, MINIMUM_NUMBER));
        //when
        AllClassroomsDto classroomsDto = classroomFacade.list();
        //then

        assertThat(classroomsDto.classroomsDto().get(0).message()).isNull();
    }
}
