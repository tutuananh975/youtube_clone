package personal.project.youtube.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import personal.project.youtube.interceptor.AccessInterceptor;
import personal.project.youtube.service.ChannelService;
import personal.project.youtube.service.UserRoleChannelService;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final ChannelService channelService;
    private final UserRoleChannelService userRoleChannelService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessInterceptor(channelService, userRoleChannelService));
    }
}
