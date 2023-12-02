package personal.project.youtube.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import personal.project.youtube.exeption.AppException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    Logger logger = LoggerFactory.getLogger(AuthEntryPoint.class);
    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        logger.error(accessDeniedException.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        Map<String, Object> data = new HashMap<>();
        data.put("message", accessDeniedException.getMessage());
        data.put("code", HttpStatus.FORBIDDEN.value());
        data.put("status", HttpStatus.FORBIDDEN.name());

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, data);
        out.flush();
    }
}
