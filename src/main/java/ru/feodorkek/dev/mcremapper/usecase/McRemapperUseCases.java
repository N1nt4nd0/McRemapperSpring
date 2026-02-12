package ru.feodorkek.dev.mcremapper.usecase;

import ru.feodorkek.dev.mcremapper.dto.request.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.MaybeRemapResponse;

public interface McRemapperUseCases {
    
    MaybeRemapResponse maybeRemap( MaybeRemapRequest request );
    
}