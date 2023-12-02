package personal.project.youtube.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LoginRequest {
    @NotEmpty(message = "username is required!")
    @Schema(
            description = "username of user",
            name = "username",
            type = "String",
            example = "tutuananh975"
    )
    private String username;

    @NotEmpty(message = "password is required!")
    @Schema(
            description = "password of user",
            name = "password",
            type = "String",
            example = "123456Aa@")
    private String password;
}
