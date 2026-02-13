package ru.feodorkek.dev.mcremapper.usecase;

import ru.feodorkek.dev.mcremapper.dto.request.RemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.RemapResponse;

public interface McRemapperUseCases {
    
    RemapResponse maybeRemap( RemapRequest request );
    
}