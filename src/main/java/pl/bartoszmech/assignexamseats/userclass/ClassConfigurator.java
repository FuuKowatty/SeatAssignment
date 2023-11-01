package pl.bartoszmech.assignexamseats.userclass;

public class ClassConfigurator {
    public ClassFacade classFacadeForTest(ClassRepository repository) {
        return new ClassFacade(new ClassValidator(), repository, new ClassMapper());
    }
};
