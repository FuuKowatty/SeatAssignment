package pl.bartoszmech.assignexamseats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    //handling custom validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorDetails response = new ErrorDetails();
        response.setMessage("Validation Failed");
        response.setDetails(errors);
        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    //handle not found
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorDetails> handleStudentNotFoundException(NotFound e) {
        ErrorDetails response = new ErrorDetails();
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND);
    }

    //handle email taken
    @ExceptionHandler(UserEmailTakenException.class)
    public ResponseEntity<ErrorDetails> handleUserEmailTakenException(UserEmailTakenException userEmailTakenException) {
        ErrorDetails response = new ErrorDetails();
        response.setMessage(userEmailTakenException.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }

    //handle student nickname taken
    @ExceptionHandler(StudentNicknameTaken.class)
    public ResponseEntity<ErrorDetails> handleStudentNicknameTakenException(StudentNicknameTaken StudentNicknameTaken) {
        ErrorDetails response = new ErrorDetails();
        response.setMessage(StudentNicknameTaken.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }

    //handle classroom name taken
    @ExceptionHandler(ClassroomNameTaken.class)
    public ResponseEntity<ErrorDetails> handleClassroomTakenException(ClassroomNameTaken classroomNameTaken) {
        ErrorDetails response = new ErrorDetails();
        response.setMessage(classroomNameTaken.getMessage());
        return ResponseEntity.status(CONFLICT).body(response);
    }

    @ExceptionHandler(AuthenticationError.class)
    public ResponseEntity<ErrorDetails> handleClassroomTakenException(AuthenticationError authenticationError) {
        ErrorDetails response = new ErrorDetails();
        response.setMessage(authenticationError.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}
