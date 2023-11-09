package pl.bartoszmech.assignexamseats.exception;

public class ClassroomNameTaken extends RuntimeException {
    public ClassroomNameTaken(String message) {
        super(message);
    }

}
