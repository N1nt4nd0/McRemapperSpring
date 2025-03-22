package ru.feodorkek.dev.mcremapper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import ru.feodorkek.dev.mcremapper.config.properties.converters.PatternConverter;

@Configuration
@RequiredArgsConstructor
public class PropertiesConverterConfig {

    private final PatternConverter patternConverter;

    @Bean
    public DefaultConversionService defaultConversionService() {
        return new DefaultConversionService();
    }

    @Bean
    public ConversionService conversionService(final DefaultConversionService defaultConversionService) {
        defaultConversionService.addConverter(patternConverter);
        return defaultConversionService;
    }

}
