package ru.feodorkek.dev.mcremapper.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("web.rest.endpoints")
public class RestEndpointsProperties {

    private String ping;
    private String maybeRemap;

}
