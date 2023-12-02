package personal.project.youtube.exeption;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppException extends Exception {
    private HttpStatus httpStatus;
    public AppException(String message) {
        super(message);
    }

    public AppException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
