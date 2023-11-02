package pl.bartoszmech.assignexamseats.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.bartoszmech.assignexamseats.student.dto.StudentDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.bartoszmech.assignexamseats.student.StudentValidationEnum.*;
import static pl.bartoszmech.assignexamseats.student.StudentValidator.*;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.SUCCESS_MESSAGE;

public class StudentFacadeTest {
    StudentFacade studentFacade;
    StudentRepository repository = new StudentRepositoryImpl();
    @BeforeEach
    public void initClassroomFacade() {
        studentFacade = new StudentConfigurator().studentFacadeForTest(repository);
    }
    final String randomStringWithoutDigits = "KtGwgxCxTwbskCxLlkDMrSIBrXXaHjvUzrLPLmWizwzcbinvfoQYFICMblBYjJOljrjzHDKAGYZMOBDVdqydcXfDabBNzwGgVBBt";


    @Test
    public void should_return_dto_with_success_message_when_provide_valid_properties() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MAXIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(SUCCESS_MESSAGE);
        assertThat(studentDto).isInstanceOf(StudentDto.class);
    }

    @Test
    public void should_return_name_too_long_error_when_provide_too_long_first_name() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MAXIMUM_CHARACTERS+1);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(FIRST_NAME_TOO_LONG.message);
    }

    @Test
    public void should_return_name_too_short_error_when_provide_too_short_first_name() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS-1);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(FIRST_NAME_TOO_SHORT.message);
    }

    @Test
    public void should_return_null_error_when_provide_null_first_name() {
        //given
        String firstName = null;
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(FIRST_NAME_NULL.message);
    }

    @Test
    public void should_return_digits_error_if_first_name_contains_digits() {
        //given
        String firstName = "1A1a1A1a1A1a".substring(0, MINIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(FIRST_NAME_DIGITS_NOT_ALLOWED.message);
    }

    @Test
    public void should_return_name_too_long_error_when_provide_too_long_last_name() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MAXIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MAXIMUM_CHARACTERS+1);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(LAST_NAME_TOO_LONG.message);
    }

    @Test
    public void should_return_name_too_short_error_when_provide_too_short_last_name() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MAXIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS-1);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(LAST_NAME_TOO_SHORT.message);
    }

    @Test
    public void should_return_null_error_when_provide_null_last_name() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);;
        String lastName = null;
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(LAST_NAME_NULL.message);
    }

    @Test
    public void should_return_digits_error_if_last_name_contains_digits() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        String lastName = "1A1a1A1a1A1a".substring(0, MINIMUM_CHARACTERS);
        Byte age = 30;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(LAST_NAME_DIGITS_NOT_ALLOWED.message);
    }

    @Test
    public void should_return_null_error_when_provide_null_age() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = null;
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(AGE_NULL.message);
    }

    @Test
    public void should_return_range_error_when_provide_too_small_age() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = (byte) (MINIMUM_AGE - 1);
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(AGE_RANGE_ERROR.message);
    }

    @Test
    public void should_return_range_error_when_provide_too_big_age() {
        //given
        String firstName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        String lastName = randomStringWithoutDigits.substring(0, MINIMUM_CHARACTERS);
        Byte age = (byte) (MAXIMUM_AGE + 1);
        StudentDto classLayoutFromUser = new StudentDto(firstName, lastName, age);
        //when
        StudentDto studentDto = studentFacade.create(classLayoutFromUser);
        //then
        assertThat(studentDto.message()).isEqualTo(AGE_RANGE_ERROR.message);
    }
}
