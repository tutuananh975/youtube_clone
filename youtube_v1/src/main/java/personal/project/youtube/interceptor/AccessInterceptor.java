package personal.project.youtube.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import personal.project.youtube.config.CustomeUserDetails;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.service.ChannelService;
import personal.project.youtube.service.UserRoleChannelService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AccessInterceptor implements HandlerInterceptor {
    private final ChannelService channelService;
    private final UserRoleChannelService userRoleChannelService;
    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception {
        String channelIdStr = (String) request.getAttribute("channelId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = ((CustomeUserDetails) authentication.getPrincipal()).getUser();

        if (channelIdStr == null || currentUser == null) {
            return false;
        }

        Long channelId = Long.parseLong(channelIdStr);

        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(HasPermission.class)) {
                HasPermission hasPermissionAnnotation = method.getAnnotation(HasPermission.class);
                String roleParam = hasPermissionAnnotation.value();

                Channel channel = channelService.findById(channelId).orElseThrow(() ->
                        new AppException("Cannot find channel with id = " + channelId));


                List<String> rolesOfUser = userRoleChannelService.findByUserAndChannel(currentUser, channel)
                        .stream().map(Role::getRoleName)
                        .toList();
                if (rolesOfUser.isEmpty() || !rolesOfUser.contains(roleParam)) {
                    sendError(response);
                    return false;
                }
            }
        }
        return true;
    }

    private void sendError(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
    }
}
