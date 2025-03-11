package ru.feodorkek.dev.mcremapper.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("mc-remapper.web.rest.endpoints.public")
public class RestPublicEndpointsProperties {

    private String ping;
    private String mcRemapperInfo;
    private String mcRemapperMaybeRemap;

}
