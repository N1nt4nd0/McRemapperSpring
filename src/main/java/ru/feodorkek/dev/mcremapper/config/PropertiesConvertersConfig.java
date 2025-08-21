package ru.feodorkek.dev.mcremapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.regex.Pattern;

@Configuration
public class PropertiesConvertersConfig {

    @Bean
    public ConversionService conversionService() {
        final var conversionService = new DefaultConversionService();
        conversionService.addConverter(String.class, Pattern.class, Pattern::compile);
        return conversionService;
    }

}
