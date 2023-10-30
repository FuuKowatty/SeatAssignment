package pl.bartoszmech.assignexamseats.classroom;

record LayoutValidationResult(
        String resultMessage
) {
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static LayoutValidationResult success() {
        return new LayoutValidationResult(SUCCESS_MESSAGE);
    }
    public static LayoutValidationResult failure(String message) {
        return new LayoutValidationResult(message);
    }
}
