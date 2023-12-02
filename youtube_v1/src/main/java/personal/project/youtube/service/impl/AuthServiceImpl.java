package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import personal.project.youtube.config.JwtService;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.UserType;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.mapper.UserMapper;
import personal.project.youtube.mapper.UserMapperImpl;
import personal.project.youtube.payload.request.LoginRequest;
import personal.project.youtube.payload.request.RegisterRequest;
import personal.project.youtube.repository.UserRepository;
import personal.project.youtube.service.AuthService;
import personal.project.youtube.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper = new UserMapperImpl();
    @Override
    public Map<String, String> login(LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken(loginRequest.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }

    @Override
    public Map<String, String> register(RegisterRequest registerRequest) throws AppException {
        Optional<User> foundUserByUsername = userRepository.findByUsername(registerRequest.getUsername());
        if (foundUserByUsername.isPresent()) {
            throw new AppException("Username is already exist!");
        }
        Optional<User> foundUserByEmail = userRepository.findByEmail(registerRequest.getEmail());
        if (foundUserByEmail.isPresent()) {
            throw new AppException("Email is already exist!");
        }
        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setType(UserType.USER.USER);
        userService.save(user);
        Map<String, String> response = new HashMap<>();
        response.put("message", "register successfully!");
        return  response;
    }
}
