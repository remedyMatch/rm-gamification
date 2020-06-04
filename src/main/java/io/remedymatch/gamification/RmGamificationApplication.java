package io.remedymatch.gamification;

import io.remedymatch.gamification.properties.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({EngineProperties.class, BackendProperties.class})
public class RmGamificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmGamificationApplication.class, args);
    }

}
