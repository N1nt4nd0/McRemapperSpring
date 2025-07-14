package ru.feodorkek.dev.mcremapper.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoResponse;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;

@Service
@RequiredArgsConstructor
public class McRemapperUseCasesImpl implements McRemapperUseCases {

    private final McRemapperService remapperService;

    @Override
    public MaybeRemapResponse maybeRemap(final MaybeRemapRequest request) {
        remapperService.setCurrentProvider(request.getMcRemapperProviderName());
        final var remapResult = remapperService.maybeRemap(request.getMappedSource());
        return new MaybeRemapResponse(remapResult);
    }

    @Override
    public McRemapperInfoResponse getInfo() {
        return new McRemapperInfoResponse(remapperService.getRegisteredProviderNames(),
                remapperService.getMaybeRemapSourceMinLen(), remapperService.getMaybeRemapSourceMaxLen());
    }

}
