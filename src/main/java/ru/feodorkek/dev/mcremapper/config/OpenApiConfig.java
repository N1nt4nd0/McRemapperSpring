package ru.feodorkek.dev.mcremapper.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.properties.OpenApiInfoProperties;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final OpenApiInfoProperties infoProperties;

    @Bean
    public OpenAPI openApiDefinition() {
        return new OpenAPI().info(new Info()
                .title(infoProperties.getTitle())
                .version(infoProperties.getVersion())
                .contact(new Contact().email(infoProperties.getAuthorEmail())));
    }

}
