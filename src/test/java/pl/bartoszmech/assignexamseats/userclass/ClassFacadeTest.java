package pl.bartoszmech.assignexamseats.userclass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl.bartoszmech.assignexamseats.userclass.dto.AllClassesDto;
import pl.bartoszmech.assignexamseats.userclass.dto.ClassDto;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static pl.bartoszmech.assignexamseats.userclass.ClassValidator.MAXIMUM_CHARACTERS;
import static pl.bartoszmech.assignexamseats.userclass.ClassValidator.MINIMUM_CHARACTERS;
import static pl.bartoszmech.assignexamseats.validatorResult.ValidatorResultFacade.SUCCESS_MESSAGE;

public class ClassFacadeTest {

    ClassRepositoryImpl repository = new ClassRepositoryImpl();
    ClassFacade classFacade;
    final String randomString = "ZzRTtU9dpRVDWOorD8Uso6K3YdaYD5y0aQWIwOSDqET6fy2JCA43KrHKtJuUza6sKcf1rHDu5nutO1PH6ArTmD97cCU8fb8XCtHS";

    @BeforeEach
    public void initClassFacade() {
        classFacade = new ClassConfigurator().classFacadeForTest(repository);
    }
    @Test
    public void should_return_class_dto_on_success() {
        //given
        String name = randomString.substring(0, MAXIMUM_CHARACTERS);
        ClassDto classDto = new ClassDto(name);
        //when
        ClassDto result = classFacade.create(classDto);
        //then
        assertThat(result).isInstanceOf(ClassDto.class);
        assertThat(result.name()).isEqualTo(name);
        assertThat(result.message()).isEqualTo(SUCCESS_MESSAGE);
    }

    @Test
    public void should_return_name_too_long_error_when_provide_too_long_name() {
        //given
        String name = randomString.substring(0, MAXIMUM_CHARACTERS+1);
        ClassDto inputClassDto = new ClassDto(name);
        String expectedMessage = ClassValidationEnum.NAME_TOO_LONG.message;
        //when
        ClassDto classroomResponseDto = classFacade.create(inputClassDto);
        //then
        Assertions.assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_name_too_short_error_when_provide_too_short_name() {
        //given
        String name = randomString.substring(0, MINIMUM_CHARACTERS-1);
        ClassDto inputClassDto = new ClassDto(name);
        String expectedMessage = ClassValidationEnum.NAME_TOO_SHORT.message;
        //when
        ClassDto classResponseDto = classFacade.create(inputClassDto);
        //then
        Assertions.assertThat(classResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_name_null_error_when_provide_name_null() {
        //given
        String name = null;
        ClassDto inputClassDto = new ClassDto(null);
        String expectedMessage = ClassValidationEnum.NAME_NULL.message;
        //when
        ClassDto classroomResponseDto = classFacade.create(inputClassDto);
        //then
        Assertions.assertThat(classroomResponseDto.message()).isEqualTo(expectedMessage);
    }

    @Test
    public void should_return_all_classes() {
        //given
        String name = randomString.substring(0, MAXIMUM_CHARACTERS);
        UserClass class1 = repository.save(new UserClass(name));
        List<ClassDto> expectedDto = List.of(new ClassDto(name));
        //when
        AllClassesDto classesDto = classFacade.list();
        //then
        assertThat(classesDto.classesDto().size()).isEqualTo(expectedDto.size());
        assertThat(classesDto.classesDto().get(0).name()).isEqualTo(expectedDto.get(0).name());
        assertThat(classesDto.classesDto().get(0)).isInstanceOf(ClassDto.class);
    }

    @Test
    public void should_return_list_with_null_messages_when_list() {
        //given
        String name = randomString.substring(0, MAXIMUM_CHARACTERS);
        UserClass class1 = repository.save(new UserClass(name));
        //when
        AllClassesDto classesDto = classFacade.list();
        //then
        assertThat(classesDto.classesDto().get(0).message()).isNull();
    }

}
