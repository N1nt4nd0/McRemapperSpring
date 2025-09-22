package ru.feodorkek.dev.mcremapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.domain.McRemapperProvider;
import ru.feodorkek.dev.mcremapper.properties.McRemapperProperties;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;
import ru.feodorkek.dev.mcremapper.service.McRemapperServiceImpl;

@Configuration
public class McRemapperConfig {

    @Bean
    public McRemapperService mcRemapperService(final MappingsLoaderService mappingsLoaderService,
                                               final McRemapperProperties remapperProperties) {
        final var remapperService = new McRemapperServiceImpl(remapperProperties.getMaybeRemapSourceMinLen(),
                remapperProperties.getMaybeRemapSourceMaxLen());

        for (final var propertyProvider : remapperProperties.getProviders().values()) {
            final var methodsMappings = mappingsLoaderService.loadMappingsFromResourcePath(
                    propertyProvider.getMethodsResourcePath());
            final var fieldsMappings = mappingsLoaderService.loadMappingsFromResourcePath(
                    propertyProvider.getFieldsResourcePath());
            final var remapperProvider = new McRemapperProvider(propertyProvider.getOrder(), propertyProvider.getName(),
                    propertyProvider.getRemapPattern());
            remapperProvider.loadMappings(methodsMappings);
            remapperProvider.loadMappings(fieldsMappings);
            remapperService.registerProvider(remapperProvider);
        }

        return remapperService;
    }

}
