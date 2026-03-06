package ru.feodorkek.dev.mcremapper.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import lombok.extern.slf4j.Slf4j;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;
import ru.feodorkek.dev.mcremapper.dto.EntryPosition;
import ru.feodorkek.dev.mcremapper.dto.RemapEntry;
import ru.feodorkek.dev.mcremapper.dto.RemapResult;
import ru.feodorkek.dev.mcremapper.exception.RemappingException;
import ru.feodorkek.dev.mcremapper.properties.RemapperProperties;
import ru.feodorkek.dev.mcremapper.service.RemappingService;

@Slf4j
public class RemappingServiceImpl implements RemappingService {
    
    private final Map<String, RemapperProvider> providers;
    private final RemapperProperties properties;
    
    public RemappingServiceImpl( final RemapperProperties properties ) {
        this.providers = new HashMap<>();
        this.properties = properties;
    }
    
    @Override
    public List<String> getRegisteredProvidersNames() {
        return providers.values()
                        .stream()
                        .sorted()
                        .map( RemapperProvider::getName )
                        .toList();
    }
    
    @Override
    public void registerProvider( final RemapperProvider provider ) {
        providers.put( provider.getName(), provider );
    }
    
    @Override
    public RemapResult maybeRemap( final String providerName,
                                   final String mappedSource ) {
        if( mappedSource == null || mappedSource.isBlank() ) {
            throw new RemappingException( "Mapped source cannot be blank" );
        }
        
        if( mappedSource.length() <= properties.getRemapSourceMinLen() ) {
            throw new RemappingException( "Mapped source length should be greater than {0} chars", properties.getRemapSourceMinLen() );
        } else if( mappedSource.length() >= properties.getRemapSourceMaxLen() ) {
            throw new RemappingException( "Mapped source length should be less than {0} chars", properties.getRemapSourceMaxLen() );
        }
        
        if( providerName == null || providerName.isBlank() ) {
            throw new RemappingException( "Minecraft remapper provider name cannot be blank" );
        }
        
        if( !providers.containsKey( providerName ) ) {
            throw new RemappingException( "Minecraft remapper provider {0} not registered", providerName );
        }
        
        final var provider = providers.get( providerName );
        
        final var result = new StringBuilder();
        final var remapEntries = new ArrayList<RemapEntry>();
        final var lines = mappedSource.split( "\n" );
        
        try {
            for( var lineNumber = 0; lineNumber < lines.length; lineNumber++ ) {
                final var line = lineNumber < lines.length - 1 ? lines[ lineNumber ] + "\n" : lines[ lineNumber ];
                
                final var matcher = provider.getRemapPattern().matcher( line );
                
                var offset = 0;
                
                while( matcher.find() ) {
                    final var mappedValue = matcher.group();
                    final var remappedValue = provider.getMappings().getOrDefault( mappedValue, mappedValue );
                    
                    matcher.appendReplacement( result, Matcher.quoteReplacement( remappedValue ) );
                    
                    if( !mappedValue.equals( remappedValue ) ) {
                        final var newStart = matcher.start() + offset;
                        final var newEnd = newStart + remappedValue.length();
                        offset += remappedValue.length() - mappedValue.length();
                        final var startPosition = new EntryPosition( lineNumber, newStart );
                        final var endPosition = new EntryPosition( lineNumber, newEnd );
                        remapEntries.add( new RemapEntry( startPosition, endPosition ) );
                    }
                }
                matcher.appendTail( result );
            }
            
            return new RemapResult( remapEntries.size(),
                                    remapEntries,
                                    result.toString() );
        } catch( final Exception exception ) {
            log.error( "Exception on remapping source: {}", exception.getMessage(), exception );
            throw new RemappingException( "Exception on remapping source: {0}", exception, exception.getMessage() );
        }
    }
    
}