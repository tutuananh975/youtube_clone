package personal.project.youtube.constants;

import java.util.List;

public class AppConstant {
    public static final String API_URL_PREFIX = "/api/v1/";
    public static final String API_DESTINATION_PREFIX = API_URL_PREFIX + "app";
    public static final String API_URL_AUTH_RESOURCE = API_URL_PREFIX + "auth";
    public static final String API_URL_VIDEO_RESOURCE = API_URL_PREFIX + "video";
    public static final String API_URL_SUBSCRIBE_RESOURCE = API_URL_PREFIX + "subscribe";

    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_EDITOR = "EDITOR";

    public static final List<String> CHANNEL_ROLES = List.of(ROLE_ADMIN, ROLE_EDITOR);
}
