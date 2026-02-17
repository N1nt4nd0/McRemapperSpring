package ru.feodorkek.dev.mcremapper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;
import ru.feodorkek.dev.mcremapper.properties.RemapperProperties;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;
import ru.feodorkek.dev.mcremapper.service.RemappingService;
import ru.feodorkek.dev.mcremapper.service.impl.RemappingServiceImpl;

@Slf4j
@Configuration
public class McRemapperConfig {
    
    @Bean
    public RemappingService createRemappingService( final MappingsLoaderService mappingsLoaderService,
                                                    final RemapperProperties properties ) {
        log.info( "RemappingService creating start with {} providers", properties.getProviders().size() );
        
        final var remappingService = new RemappingServiceImpl( properties );
        
        for( final var providerProperty : properties.getProviders().values() ) {
            registerProvider( providerProperty, mappingsLoaderService, remappingService );
        }
        
        log.info( "RemappingService created successfully. Registered providers: {}", remappingService.getRegisteredProvidersNames() );
        return remappingService;
    }
    
    private void registerProvider( final RemapperProperties.RemapperProviderProperty providerProperty,
                                   final MappingsLoaderService mappingsLoaderService,
                                   final RemappingService remappingService ) {
        log.info( "Registering provider with property: {}", providerProperty );
        
        final var methodsMappings = mappingsLoaderService.loadMappingsFromResource( providerProperty.getMethodsResourcePath() );
        final var fieldsMappings = mappingsLoaderService.loadMappingsFromResource( providerProperty.getFieldsResourcePath() );
        
        final var provider = new RemapperProvider( providerProperty.getOrder(),
                                                   providerProperty.getName(),
                                                   providerProperty.getRemapPattern() );
        
        provider.loadMappings( methodsMappings );
        provider.loadMappings( fieldsMappings );
        
        remappingService.registerProvider( provider );
        
        log.info( "Provider {} successfully registered with mappings count: {} methods, {} fields, {} total",
                  provider.getName(),
                  methodsMappings.size(),
                  fieldsMappings.size(),
                  provider.getMappings().size() );
    }
    
}