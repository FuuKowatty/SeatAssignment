package pl.bartoszmech.assignexamseats.student;

public class StudentConfigurator {
    public StudentFacade studentFacadeForTest(StudentRepository repository) {
        return new StudentFacade(new StudentValidator(), repository, new StudentMapper());
    }
}
