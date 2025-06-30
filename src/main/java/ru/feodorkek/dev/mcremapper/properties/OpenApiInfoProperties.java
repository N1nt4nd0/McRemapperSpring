package ru.feodorkek.dev.mcremapper.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("mc-remapper.web.rest.api-info")
public class OpenApiInfoProperties {

    private String title;
    private String version;
    private String authorEmail;

}
