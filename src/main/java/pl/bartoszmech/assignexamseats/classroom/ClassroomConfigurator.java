package pl.bartoszmech.assignexamseats.classroom;

public class ClassroomConfigurator {
    public ClassroomFacade classroomFacadeForTest() {
        return new ClassroomFacade(new ClassroomValidator());
    }
}
