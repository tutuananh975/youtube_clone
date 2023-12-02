package personal.project.youtube.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import personal.project.youtube.constants.AppConstant;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.payload.request.LoginRequest;
import personal.project.youtube.payload.request.RegisterRequest;
import personal.project.youtube.payload.response.ErrorResponse;
import personal.project.youtube.payload.response.ResponseObject;
import personal.project.youtube.service.AuthService;

@RestController
@RequestMapping(AppConstant.API_URL_AUTH_RESOURCE)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "register")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "register successfully!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class))}),
            @ApiResponse(responseCode = "400", description = "register failure!",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) throws AppException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject<>(HttpStatus.CREATED, authService.register(registerRequest))
        );
    }

    @Operation(summary = "login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "login successfully!",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseObject.class))}),
            @ApiResponse(responseCode = "400", description = "authentication failure because username or password is empty",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "authentication failure because incorrect username or password!",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject<>(HttpStatus.CREATED, authService.login(loginRequest))
        );
    }
//    @Operation(summary = "test authentication")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "authentication successfully!",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = RegisterRequest.class))}),
//            @ApiResponse(responseCode = "401", description = "authentication failure because incorrect username or password!",
//                    content = {@Content(mediaType = "application/json",
//                            schema = @Schema(implementation = RegisterRequest.class))})
//    })
//    @GetMapping("/test-auth")
//    public ResponseEntity<?> testAuth() {
//        return ResponseEntity.ok("Authentication successfully!");
//    }
}
