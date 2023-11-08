package pl.bartoszmech.assignexamseats.exception;

public class UserEmailTakenException extends RuntimeException {
    public UserEmailTakenException(String message) {
        super(message);
    }
}
