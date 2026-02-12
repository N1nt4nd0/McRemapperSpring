package ru.feodorkek.dev.mcremapper.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.Getter;
import ru.feodorkek.dev.mcremapper.exception.MappingsLoadException;

@Getter
public class RemapperProvider implements Comparable<RemapperProvider> {
    
    private final int order;
    private final String name;
    private final Pattern remapPattern;
    private final Map<String, String> mappings;
    
    public RemapperProvider( final int order,
                             final String name,
                             final Pattern remapPattern ) {
        this.order = order;
        this.name = name;
        this.remapPattern = remapPattern;
        this.mappings = new HashMap<>();
    }
    
    public Map<String, String> getMappings() {
        return Collections.unmodifiableMap( mappings );
    }
    
    public void loadMappings( final Map<String, String> mappings ) {
        if( mappings == null || mappings.isEmpty() ) {
            throw new MappingsLoadException( "No input mappings found" );
        }
        
        this.mappings.putAll( mappings );
    }
    
    @Override
    public int compareTo( final RemapperProvider another ) {
        return another.order - order;
    }
    
}