package pl.bartoszmech.assignexamseats.classroom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MAXIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.classroom.ClassroomValidator.MINIMUM_NUMBER;
import static pl.bartoszmech.assignexamseats.classroom.LayoutValidationResult.SUCCESS_MESSAGE;

public class ClassroomFacadeTest {

    private ClassroomFacade classroomFacade;
    private Map<String, Integer> classLayoutFromUser;
    @BeforeEach
    public void initClassroomFacade() {
        classroomFacade = new ClassroomConfigurator().classroomFacadeForTest();
        classLayoutFromUser = new HashMap<>();
    }

    @Test
    public void should_return_dto_with_success_message_when_provide_valid_columns_and_rows() {
        //given
        classLayoutFromUser.put("columns", MAXIMUM_NUMBER);
        classLayoutFromUser.put("rows", MINIMUM_NUMBER);
        ClassroomDto expectedDto = new ClassroomDto(SUCCESS_MESSAGE);
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedDto.message());
        assertThat(classroomDto).isInstanceOf(ClassroomDto.class);
    }

    @Test
    public void should_return_column_too_big_error_when_provide_too_big_column() {
        //given
        classLayoutFromUser.put("columns", MAXIMUM_NUMBER + 1);
        classLayoutFromUser.put("rows", MINIMUM_NUMBER);
        String expectedMessage = ValidationEnum.COLUMNS_TOO_BIG.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_too_small_error_when_provide_too_small_column() {
        //given
        classLayoutFromUser.put("columns", MINIMUM_NUMBER - 1);
        classLayoutFromUser.put("rows", MINIMUM_NUMBER);
        String expectedMessage = ValidationEnum.COLUMNS_TOO_SMALL.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_null_error_when_provide_column_null() {
        //given
        classLayoutFromUser.put("columns", null);
        classLayoutFromUser.put("rows", MINIMUM_NUMBER);
        String expectedMessage = ValidationEnum.COLUMNS_NULL.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_big_error_when_provide_too_big_row() {
        //given
        classLayoutFromUser.put("columns", MAXIMUM_NUMBER);
        classLayoutFromUser.put("rows", MAXIMUM_NUMBER + 1);
        String expectedMessage = ValidationEnum.ROWS_TOO_BIG.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_small_error_when_provide_too_small_row() {
        //given
        classLayoutFromUser.put("columns", MINIMUM_NUMBER);
        classLayoutFromUser.put("rows", MINIMUM_NUMBER - 1);
        String expectedMessage = ValidationEnum.ROWS_TOO_SMALL.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_null_error_when_provide_row_null() {
        //given
        classLayoutFromUser.put("columns", MINIMUM_NUMBER);
        classLayoutFromUser.put("rows", null);
        String expectedMessage = ValidationEnum.ROWS_NULL.message;
        //when
        ClassroomDto classroomDto = classroomFacade.inputClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomDto.message()).isEqualTo(expectedMessage);
    }
}
