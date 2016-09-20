package bo.edu.ucbcba.videoclub.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Validation error: " + message);
    }
}
