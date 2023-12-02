package personal.project.youtube.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import personal.project.youtube.payload.response.ErrorResponse;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException {
    @ExceptionHandler({AppException.class})
    public ResponseEntity<?> handleAppException(AppException e) {
        HttpStatus status = e.getHttpStatus();
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        return getResponseEntity(status, e.getMessage(), e);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = createErrorResponse(HttpStatus.BAD_REQUEST, errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({FileNotFoundException.class})
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException e) {
        return getResponseEntity(HttpStatus.NOT_FOUND, "File Not Found", e);
    }

    private ResponseEntity<?> getResponseEntity(HttpStatus status, String message, Exception e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", message);
        ErrorResponse errorResponse = createErrorResponse(status, errors);
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ErrorResponse createErrorResponse(HttpStatus status, Map<String, String> errors) {
        return ErrorResponse.builder()
                .code(status.value())
                .status(status.name())
                .errors(errors)
                .build();
    }

}
