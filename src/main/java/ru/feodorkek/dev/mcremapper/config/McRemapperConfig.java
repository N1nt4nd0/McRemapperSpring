package ru.feodorkek.dev.mcremapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.config.properties.McRemapperProperties;
import ru.feodorkek.dev.mcremapper.core.McRemapperProvider;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;
import ru.feodorkek.dev.mcremapper.service.impl.McRemapperServiceImpl;

@Configuration
public class McRemapperConfig {

    @Bean
    public McRemapperService mcRemapperService(final MappingsLoaderService mappingsLoaderService,
                                               final McRemapperProperties remapperProperties) {

        final var mcRemapperService = new McRemapperServiceImpl(remapperProperties.getMaybeRemapSourceMinLen(),
                remapperProperties.getMaybeRemapSourceMaxLen());

        for (final var propertyProvider : remapperProperties.getProvidersList()) {

            final var methodsMappings = mappingsLoaderService.loadMappingsFromResourcePath(
                    propertyProvider.getMethodsResourcePath());
            final var fieldsMappings = mappingsLoaderService.loadMappingsFromResourcePath(
                    propertyProvider.getFieldsResourcePath());

            final var mcRemapperProvider = new McRemapperProvider(
                    propertyProvider.getOrder(),
                    propertyProvider.getName(),
                    propertyProvider.getRemapPattern());

            mcRemapperProvider.loadMappings(methodsMappings);
            mcRemapperProvider.loadMappings(fieldsMappings);
            mcRemapperService.registerProvider(mcRemapperProvider);
        }

        return mcRemapperService;
    }

}
