package pl.bartoszmech.assignexamseats.exception;


import pl.bartoszmech.assignexamseats.auth.AuthenticationErrorEnum;

public class AuthenticationError extends RuntimeException {
    private final AuthenticationErrorEnum type;
    private final String message;

    public AuthenticationError(AuthenticationErrorEnum type, String message) {
        this.type = type;
        this.message = message;
    }

    public AuthenticationErrorEnum getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
