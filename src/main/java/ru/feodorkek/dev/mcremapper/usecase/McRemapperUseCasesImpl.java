package ru.feodorkek.dev.mcremapper.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.dto.request.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;

@Service
@RequiredArgsConstructor
public class McRemapperUseCasesImpl implements McRemapperUseCases {

    private final McRemapperService remapperService;

    @Override
    public MaybeRemapResponse maybeRemap(final MaybeRemapRequest request) {
        remapperService.setCurrentProvider(request.getMcRemapperProviderName());
        return new MaybeRemapResponse(remapperService.maybeRemap(request.getMappedSource()));
    }

}
