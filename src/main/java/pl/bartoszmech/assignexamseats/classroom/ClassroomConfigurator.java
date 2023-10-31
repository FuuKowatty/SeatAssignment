package pl.bartoszmech.assignexamseats.classroom;

public class ClassroomConfigurator {
    public ClassroomFacade classroomFacadeForTest(ClassroomRepository repository) {
        return new ClassroomFacade(new ClassroomValidator(), repository, new ClassroomMapper());
    }
}
