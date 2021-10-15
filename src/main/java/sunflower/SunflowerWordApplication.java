package sunflower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
public class SunflowerWordApplication {
    public static void main(String[] args) {
        SpringApplication.run(SunflowerWordApplication.class, args);
    }
}
