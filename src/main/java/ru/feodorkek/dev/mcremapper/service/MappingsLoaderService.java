package ru.feodorkek.dev.mcremapper.service;

import java.util.Map;

public interface MappingsLoaderService {
    
    Map<String, String> loadMappingsFromResource( String resourcePath );
    
}