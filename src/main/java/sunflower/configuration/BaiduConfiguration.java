package sunflower.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "baidu")
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaiduConfiguration {
    private String grant_type;
    private String client_id;
    private String client_secret;
}
