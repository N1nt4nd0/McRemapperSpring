package ru.feodorkek.dev.mcremapper.properties;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties( "mc-remapper" )
public class RemapperProperties {
    
    @Valid
    private Map<String, RemapperProviderProperty> providers;
    
    @Positive
    private int remapSourceMinLen;
    
    @Positive
    private int remapSourceMaxLen;
    
    @Getter
    @Setter
    @ToString
    public static class RemapperProviderProperty {
        
        @Min( 0 )
        private int order;
        
        @NotBlank
        private String name;
        
        @NotNull
        private Pattern remapPattern;
        
        @NotBlank
        private String methodsResourcePath;
        
        @NotBlank
        private String fieldsResourcePath;
        
    }
    
}