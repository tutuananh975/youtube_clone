package personal.project.youtube.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterRequest {

    @NotEmpty(message = "username is required!")
    @Size(min = 8, max = 30, message = "Username must from 8 to 30 character!")
    @Schema(
            description = "username of user",
            name = "username",
            type = "String",
            example = "tutuananh975"
    )
    private String username;

    @NotEmpty(message = "password is required!")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$",
            message = "Password must contain from 8 to 20 characters, " +
                    "at least one number and both lower and uppercase letters and special characters"
    )
    @Schema(
            description = "password of user",
            name = "password",
            type = "String",
            example = "123456Aa@")
    private String password;

    @NotEmpty(message = "Display name is required!")
    @Schema(
            description = "name of user that is displayed in view",
            name = "displayName",
            type = "String",
            example = "Tu Tuan Anh")
    private String displayName;

    @NotEmpty(message = "Email is required!")
    @Email(message = "Email is not valid")
    @Schema(
            description = "email of user",
            name = "email",
            type = "String",
            example = "tutuananh975@gmail.com",
            format = "email"
    )
    private String email;
}
