package ru.feodorkek.dev.mcremapper.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.properties.ApiInfoProperties;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    
    private final ApiInfoProperties apiInfoProperties;
    
    @Bean
    public OpenAPI openApiDefinition() {
        return new OpenAPI().info( new Info().title( apiInfoProperties.getTitle() )
                                             .version( apiInfoProperties.getVersion() ) );
    }
    
}