package ru.feodorkek.dev.mcremapper.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.config.properties.OpenApiInfoProperties;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final OpenApiInfoProperties infoProperties;

    @Bean
    public OpenAPI openApiDefinition() {
        return new OpenAPI()
                .info(new Info().title(infoProperties.getTitle()).version(infoProperties.getVersion())
                        .contact(new Contact().email(infoProperties.getAuthorEmail())))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"))
                .components(new Components().addSecuritySchemes("basicAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")));
    }

}
