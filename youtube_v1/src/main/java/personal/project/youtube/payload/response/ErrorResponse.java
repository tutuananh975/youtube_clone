package personal.project.youtube.payload.response;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {
    private Integer code;
    private String status;
    private Map<String, String> errors;

}
