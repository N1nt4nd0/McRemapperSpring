package ru.feodorkek.dev.mcremapper.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties( "api-info" )
public class ApiInfoProperties {
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String version;
    
}