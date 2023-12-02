package personal.project.youtube.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.project.youtube.anotation.currentUser.CurrentUser.CurrentUser;
import personal.project.youtube.constants.AppConstant;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.Video;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.interceptor.HasPermission;
import personal.project.youtube.mapper.VideoMapper;
import personal.project.youtube.mapper.VideoMapperImpl;
import personal.project.youtube.payload.request.SaveVideoRequest;
import personal.project.youtube.payload.response.ErrorResponse;
import personal.project.youtube.payload.response.ResponseObject;
import personal.project.youtube.service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppConstant.API_URL_VIDEO_RESOURCE)
public class FileController {
    private final FileService fileService;
    private final VideoMapper videoMapper = new VideoMapperImpl();

//    @PreAuthorize("hasAuthority('USER')")
    @HasPermission(value = "ADMIN")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Upload file successfully!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class))}),
            @ApiResponse(responseCode = "400", description = "Upload failure",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<?> uploadFile(
            @RequestParam @NonNull MultipartFile file,
            @CurrentUser User currentUser
    ) throws AppException, IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject<>(
                        HttpStatus.CREATED,
                        fileService.uploadVideo(file, currentUser)
                )
        );
    }

    @PostMapping("/saveVideo")
    @Operation(summary = "Save file to db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Save file to DB successfully!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class))}),
            @ApiResponse(responseCode = "400", description = "Save file to DB failure!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<?> saveVideo(
            @RequestBody SaveVideoRequest saveVideoRequest,
            @CurrentUser User currentUser
    ) throws AppException {
        Video video = videoMapper.toVideo(saveVideoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject<>(HttpStatus.CREATED, fileService.saveVideoToDb(video))
        );
    }

    @Operation(summary = "Watch video")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Read file successfully",
                    content = {@Content(
                            mediaType = "video/mp4",
                            schema = @Schema(implementation = InputStreamResource.class))}),
            @ApiResponse(responseCode = "400", description = "Read file  failure",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/watch/{videoId}")
    public ResponseEntity<?> watchVideo(@PathVariable("videoId") @NotBlank @NumberFormat Long videoId)
            throws AppException, FileNotFoundException {
        InputStreamResource response = fileService.watchVideo(videoId);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("video/mp4"))
                .body(response);
    }

    @GetMapping("/getVideo/{videoId}")
    @Operation(summary = "Get video by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get video by id successfully!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class))}),
            @ApiResponse(responseCode = "400", description = "Save file to DB failure!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<?> getVideoById(
            @PathVariable("videoId") @NotBlank @NumberFormat Long videoId
    ) throws FileNotFoundException {
        var video = fileService.findById(videoId).orElseThrow(() ->
                new FileNotFoundException("File not found!"));
        return ResponseEntity.ok(new ResponseObject<>(video));
    }
}
