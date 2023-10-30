package pl.bartoszmech.assignexamseats.classroom;

record LayoutValidationResult(
        String resultMessage,
        boolean isValid
) {
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static LayoutValidationResult success() {
        return new LayoutValidationResult(SUCCESS_MESSAGE, true);
    }
    public static LayoutValidationResult failure(String message) {
        return new LayoutValidationResult(message, false);
    }
}
