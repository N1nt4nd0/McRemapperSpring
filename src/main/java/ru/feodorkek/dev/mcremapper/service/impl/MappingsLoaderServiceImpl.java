package ru.feodorkek.dev.mcremapper.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.exception.MappingsLoadException;
import ru.feodorkek.dev.mcremapper.service.MappingsLoaderService;

@Service
public class MappingsLoaderServiceImpl implements MappingsLoaderService {
    
    @Override
    public Map<String, String> loadMappingsFromResource( final String resourcePath ) {
        try {
            final var resource = new ClassPathResource( resourcePath );
            
            try( final var reader = new BufferedReader( new InputStreamReader( resource.getInputStream(), StandardCharsets.UTF_8 ) ) ) {
                return loadMappingsFromLines( reader.lines() );
            }
        } catch( final Exception exception ) {
            throw new MappingsLoadException( "Exception on loading mappings from resource path: {0}", exception, resourcePath );
        }
    }
    
    private Map<String, String> loadMappingsFromLines( final Stream<String> lines ) {
        return lines.map( line -> line.split( "," ) )
                    .filter( split -> split.length >= 2 )
                    .collect( Collectors.toMap( parts -> parts[ 0 ],
                                                parts -> parts[ 1 ],
                                                ( existing, duplicate ) -> existing ) );
    }
    
}