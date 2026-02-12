package ru.feodorkek.dev.mcremapper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;
import ru.feodorkek.dev.mcremapper.properties.RemapperProperties;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;
import ru.feodorkek.dev.mcremapper.service.impl.McRemapperServiceImpl;

@Configuration
public class McRemapperConfig {
    
    @Bean
    public McRemapperService createRemapperService( final MappingsLoaderService loaderService,
                                                    final RemapperProperties properties ) {
        final var service = new McRemapperServiceImpl( properties );
        
        for( final var providerProperty : properties.getProviders().values() ) {
            final var methodsMappings = loaderService.loadMappingsFromResourcePath( providerProperty.getMethodsResourcePath() );
            final var fieldsMappings = loaderService.loadMappingsFromResourcePath( providerProperty.getFieldsResourcePath() );
            
            final var provider = new RemapperProvider( providerProperty.getOrder(),
                                                       providerProperty.getName(),
                                                       providerProperty.getRemapPattern() );
            
            provider.loadMappings( methodsMappings );
            provider.loadMappings( fieldsMappings );
            
            service.registerProvider( provider );
        }
        
        return service;
    }
    
}