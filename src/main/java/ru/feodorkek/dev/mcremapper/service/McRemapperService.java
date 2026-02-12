package ru.feodorkek.dev.mcremapper.service;

import java.util.List;
import ru.feodorkek.dev.mcremapper.domain.MaybeRemapResult;
import ru.feodorkek.dev.mcremapper.domain.RemapperProvider;

public interface McRemapperService {
    
    List<String> getRegisteredProvidersNames();
    
    void registerProvider( RemapperProvider provider );
    
    void setCurrentProvider( String providerName );
    
    MaybeRemapResult maybeRemap( String mappedSource );
    
}