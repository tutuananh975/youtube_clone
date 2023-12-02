package personal.project.youtube.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveVideoRequest {

    @NotEmpty(message = "fileSrc is required!")
    @Schema(
            description = "Source of file",
            name = "fileSrc",
            type = "String",
            example = "Videos/abc/xyz"
    )
    private String fileSrc;

    @NotEmpty(message = "description is required!")
    @Schema(
            description = "description about file",
            name = "description",
            type = "String",
            example = "Bộ phim nói về ABCD EFGH!"
    )
    private String description;

    @NotEmpty(message = "tittle is required!")
    @Schema(
            description = "title of file",
            name = "title",
            type = "String",
            example = "Nghệ thuật săn quỷ và nấu mỳ"
    )
    private String title;

    @NotEmpty(message = "channel id is required!")
    @Digits(message = "channelId must be a number!", integer = 10, fraction = 0)
    @Schema(
            description = "id of channel",
            name = "channelId",
            type = "Number",
            example = "1"
    )
    private Long channelId;
}