package sunflower.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserContext {

    private static final ThreadLocal<String> user = new ThreadLocal<>();

    public static void setUser(String username) {
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("username cannot be empty");
        }
        try {
            user.set(username);
        } catch (Exception e) {
            throw new RuntimeException("setting user failed");
        }
    }

    public static String getUser() {
        return user.get();
    }
}
