package ru.feodorkek.dev.mcremapper.service;

import java.util.List;
import ru.feodorkek.dev.mcremapper.domain.RemapResult;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;

public interface RemappingService {
    
    List<String> getRegisteredProvidersNames();
    
    void registerProvider( RemapperProvider provider );
    
    void setCurrentProvider( String providerName );
    
    RemapResult maybeRemap( String mappedSource );
    
}