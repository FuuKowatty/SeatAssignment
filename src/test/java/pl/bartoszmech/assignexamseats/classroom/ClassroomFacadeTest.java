package pl.bartoszmech.assignexamseats.classroom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.classroom.dto.AllClassroomsDto;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomDto;
import pl.bartoszmech.assignexamseats.classroom.dto.ClassroomResponseDto;

import static org.assertj.core.api.Assertions.*;
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
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, MINIMUM_NUMBER);
        ClassroomResponseDto expectedDto = new ClassroomResponseDto(SUCCESS_MESSAGE, classLayoutFromUser.columns(), classLayoutFromUser.rows());
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedDto.message());
        assertThat(classroomResponseDto).isInstanceOf(ClassroomResponseDto.class);
    }

    @Test
    public void should_return_column_too_big_error_when_provide_too_big_column() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER+1, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_TOO_BIG.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_too_small_error_when_provide_too_small_column() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MINIMUM_NUMBER-1, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_TOO_SMALL.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_column_null_error_when_provide_column_null() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(null, MINIMUM_NUMBER);
        String expectedMessage = ClassroomValidationEnum.COLUMNS_NULL.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_big_error_when_provide_too_big_row() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, MAXIMUM_NUMBER+1);

        String expectedMessage = ClassroomValidationEnum.ROWS_TOO_BIG.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_too_small_error_when_provide_too_small_row() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, MINIMUM_NUMBER-1);
        String expectedMessage = ClassroomValidationEnum.ROWS_TOO_SMALL.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_row_null_error_when_provide_row_null() {
        //given
        ClassroomDto classLayoutFromUser = new ClassroomDto(MAXIMUM_NUMBER, null);
        String expectedMessage = ClassroomValidationEnum.ROWS_NULL.message;
        //when
        ClassroomResponseDto classroomResponseDto = classroomFacade.addClassroomLayout(classLayoutFromUser);
        //then
        assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_all_classrooms() {
        //given
        Classroom classroom1 = repository.save(new Classroom(5, 5));
        //when
        AllClassroomsDto classroomsDto = classroomFacade.allClasses();
        //then
        assertThat(new ClassroomDto(classroom1.getColumns(), classroom1.getRows()) ).isEqualTo(classroomsDto.classroomsDto().get(0));
    }
}
