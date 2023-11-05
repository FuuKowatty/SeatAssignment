package pl.bartoszmech.assignexamseats.validator;

public record ValidatorResult(
        String resultMessage,
        boolean isValid
) {
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static ValidatorResult success() {
        return new ValidatorResult(SUCCESS_MESSAGE, true);
    }
    public static ValidatorResult failure(String message) {
        return new ValidatorResult(message, false);
    }
}
