package personal.project.youtube.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import personal.project.youtube.anotation.currentUser.CurrentUser.CurrentUserArgumentResolver;

import java.util.List;

@Configuration
public class ArgumentResolverConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers
    ) {
        argumentResolvers.add(new CurrentUserArgumentResolver());
    }
}
