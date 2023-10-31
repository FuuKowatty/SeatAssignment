package pl.bartoszmech.assignexamseats.validatorResult;

public record ValidatorResultFacade(
        String resultMessage,
        boolean isValid
) {
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static ValidatorResultFacade success() {
        return new ValidatorResultFacade(SUCCESS_MESSAGE, true);
    }
    public static ValidatorResultFacade failure(String message) {
        return new ValidatorResultFacade(message, false);
    }
}
