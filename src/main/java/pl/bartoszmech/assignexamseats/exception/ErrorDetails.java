package pl.bartoszmech.assignexamseats.exception;

import java.util.Date;
import java.util.Map;

public class ErrorDetails {
    private String message;
    Map<String, String> details;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
