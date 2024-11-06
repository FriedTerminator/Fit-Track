package errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    // Constructor with custom message
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    // Constructor with email-specific message
    public UserAlreadyExistsException(String email, boolean isEmail) {
        super("User with email " + email + " already exists");
    }

    // Default constructor
    public UserAlreadyExistsException() {
        super("User already exists in the system");
    }

    // Optional: Global Exception Handler
    @ControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(UserAlreadyExistsException.class)
        public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(
                UserAlreadyExistsException ex,
                WebRequest request
        ) {
            ErrorResponse error = new ErrorResponse(
                    HttpStatus.CONFLICT.value(),
                    ex.getMessage(),
                    System.currentTimeMillis()
            );

            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    // Optional: Error Response DTO
    public class ErrorResponse {
        private int status;
        private String message;
        private long timestamp;

        public ErrorResponse(int status, String message, long timestamp) {
            this.status = status;
            this.message = message;
            this.timestamp = timestamp;
        }

        // Getters and setters
        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
