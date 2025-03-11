package ru.feodorkek.dev.mcremapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class ResourceLoaderConfig {

    @Bean
    public ResourceLoader resourceLoader() {
        return new DefaultResourceLoader();
    }

}
