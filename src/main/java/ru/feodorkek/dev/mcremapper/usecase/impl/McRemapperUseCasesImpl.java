package ru.feodorkek.dev.mcremapper.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.dto.request.RemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.RemapResponse;
import ru.feodorkek.dev.mcremapper.service.RemappingService;
import ru.feodorkek.dev.mcremapper.usecase.McRemapperUseCases;

@Service
@RequiredArgsConstructor
public class McRemapperUseCasesImpl implements McRemapperUseCases {
    
    private final RemappingService remappingService;
    
    @Override
    public RemapResponse maybeRemap( final RemapRequest request ) {
        remappingService.setCurrentProvider( request.getMcRemapperProviderName() );
        return new RemapResponse( remappingService.maybeRemap( request.getMappedSource() ) );
    }
    
}