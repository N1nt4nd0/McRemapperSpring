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
                                               final McRemapperProperties mcRemapperProperties) {
        final var mc1710RemapperProvider = new McRemapperProvider(
                mcRemapperProperties.getProviders().getMc1710().getName(),
                mcRemapperProperties.getProviders().getMc1710().getRemapPattern());
        mc1710RemapperProvider.loadMappings(mappingsLoaderService.loadMappingsFromResourcePath(
                mcRemapperProperties.getProviders().getMc1710().getMethodsResourcePath()));
        mc1710RemapperProvider.loadMappings(mappingsLoaderService.loadMappingsFromResourcePath(
                mcRemapperProperties.getProviders().getMc1710().getFieldsResourcePath()));

        final var mc1201RemapperProvider = new McRemapperProvider(
                mcRemapperProperties.getProviders().getMc1201().getName(),
                mcRemapperProperties.getProviders().getMc1201().getRemapPattern());
        mc1201RemapperProvider.loadMappings(mappingsLoaderService.loadMappingsFromResourcePath(
                mcRemapperProperties.getProviders().getMc1201().getMethodsResourcePath()));
        mc1201RemapperProvider.loadMappings(mappingsLoaderService.loadMappingsFromResourcePath(
                mcRemapperProperties.getProviders().getMc1201().getFieldsResourcePath()));

        final var mcRemapperService = new McRemapperServiceImpl(
                mcRemapperProperties.getMaybeRemapSourceMinLen(),
                mcRemapperProperties.getMaybeRemapSourceMaxLen());

        mcRemapperService.registerProvider(mc1710RemapperProvider);
        mcRemapperService.registerProvider(mc1201RemapperProvider);

        return mcRemapperService;
    }

}
