package ru.feodorkek.dev.mcremapper.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import ru.feodorkek.dev.mcremapper.domain.EntryPosition;
import ru.feodorkek.dev.mcremapper.domain.MaybeRemapResult;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;
import ru.feodorkek.dev.mcremapper.domain.RemapEntry;
import ru.feodorkek.dev.mcremapper.exception.RemapperProviderException;
import ru.feodorkek.dev.mcremapper.exception.RemappingException;
import ru.feodorkek.dev.mcremapper.properties.RemapperProperties;

public class McRemapperServiceImpl implements McRemapperService {
    
    private final Map<String, RemapperProvider> providers;
    private final RemapperProperties properties;
    private RemapperProvider currentProvider;
    
    public McRemapperServiceImpl( final RemapperProperties properties ) {
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
    public void setCurrentProvider( final String providerName ) {
        if( providerName == null || providerName.isBlank() ) {
            throw new RemapperProviderException( "Minecraft version name cannot be blank" );
        }
        
        if( !providers.containsKey( providerName ) ) {
            throw new RemapperProviderException( "Minecraft remapper provider {0} not registered", providerName );
        }
        
        currentProvider = providers.get( providerName );
    }
    
    @Override
    public MaybeRemapResult maybeRemap( final String mappedSource ) {
        if( currentProvider == null ) {
            throw new RemappingException( "Current provider not set" );
        }
        
        if( mappedSource == null || mappedSource.isBlank() ) {
            throw new RemappingException( "Mapped source cannot be blank" );
        }
        
        if( mappedSource.length() <= properties.getRemapSourceMinLen() ) {
            throw new RemappingException( "Mapped source length should be greater than {0} chars", properties.getRemapSourceMinLen() );
        } else if( mappedSource.length() >= properties.getRemapSourceMaxLen() ) {
            throw new RemappingException( "Mapped source length should be less than {0} chars", properties.getRemapSourceMaxLen() );
        }
        
        final var result = new StringBuilder();
        final var remapEntries = new ArrayList<RemapEntry>();
        final var lines = mappedSource.split( "\n" );
        
        try {
            for( int lineNumber = 0; lineNumber < lines.length; lineNumber++ ) {
                final var line = lineNumber < lines.length - 1 ? lines[ lineNumber ] + "\n" : lines[ lineNumber ];
                
                final var matcher = currentProvider.getRemapPattern().matcher( line );
                
                var offset = 0;
                
                while( matcher.find() ) {
                    var mappedValue = matcher.group();
                    var remappedValue = currentProvider.getMappings().getOrDefault( mappedValue, mappedValue );
                    matcher.appendReplacement( result, Matcher.quoteReplacement( remappedValue ) );
                    
                    if( !mappedValue.equals( remappedValue ) ) {
                        var newStart = matcher.start() + offset;
                        var newEnd = newStart + remappedValue.length();
                        offset += remappedValue.length() - mappedValue.length();
                        final var startPosition = new EntryPosition( lineNumber, newStart );
                        final var endPosition = new EntryPosition( lineNumber, newEnd );
                        remapEntries.add( new RemapEntry( startPosition, endPosition ) );
                    }
                }
                matcher.appendTail( result );
            }
            
            return new MaybeRemapResult( remapEntries.size(),
                                         remapEntries,
                                         result.toString() );
        } catch( final Exception exception ) {
            throw new RemappingException( "Exception on remapping source", exception );
        }
    }
    
}