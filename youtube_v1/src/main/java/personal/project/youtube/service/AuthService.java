package personal.project.youtube.service;

import org.springframework.security.core.AuthenticationException;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.payload.request.LoginRequest;
import personal.project.youtube.payload.request.RegisterRequest;

import java.util.Map;

public interface AuthService {
    Map<String, String> login(LoginRequest loginRequest) throws AuthenticationException;

    Map<String, String> register(RegisterRequest registerRequest) throws AppException;
}
