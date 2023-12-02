package personal.project.youtube.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import personal.project.youtube.constants.AppConstant;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.UserType;
import personal.project.youtube.service.RoleService;
import personal.project.youtube.service.UserService;

@RequiredArgsConstructor
@Component
public class AppRun implements CommandLineRunner {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Value("${env.isInitialize}")
    private Boolean isInitialize;

    @Override
    public void run(String... args) throws Exception {
        if (isInitialize) {
            initAdminUser();
            initRolesOfChannel();

        }

    }

    private void initRolesOfChannel() {
        AppConstant.CHANNEL_ROLES.forEach(role -> roleService.save(
                Role.builder().roleName(role).build()
        ));
    }

    private void initAdminUser() {
        User user = User.builder()
                .username("admin123456")
                .password(passwordEncoder.encode("adminAa@"))
                .displayName("TUan ANh")
                .email("admin@luvina.net")
                .type(UserType.SUPER_ADMIN)
                .build();
        userService.save(user);
    }
}
