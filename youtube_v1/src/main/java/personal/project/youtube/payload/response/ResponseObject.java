package personal.project.youtube.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseObject<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseObject(HttpStatus status, T data) {
        this.code = status.value();
        this.message = status.name();
        this.data = data;
    }

    public ResponseObject(T data) {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
        this.data = data;
    }
}
