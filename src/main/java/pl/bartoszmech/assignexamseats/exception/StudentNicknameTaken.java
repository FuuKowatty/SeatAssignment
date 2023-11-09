package pl.bartoszmech.assignexamseats.exception;

public class StudentNicknameTaken extends RuntimeException {
    public StudentNicknameTaken(String message) {
        super(message);
    }
}
