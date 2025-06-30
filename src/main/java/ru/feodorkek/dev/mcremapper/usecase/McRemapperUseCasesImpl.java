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

    private final McRemapperService mcRemapperService;

    @Override
    public MaybeRemapResponse maybeRemap(final MaybeRemapRequest request) {
        mcRemapperService.setCurrentProvider(request.getMcRemapperProviderName());
        mcRemapperService.validateMappedSource(request.getMappedSource());
        return new MaybeRemapResponse(mcRemapperService.maybeRemap(request.getMappedSource()));
    }

    @Override
    public McRemapperInfoResponse getInfo() {
        return new McRemapperInfoResponse(mcRemapperService.getRegisteredProviderNames(),
                mcRemapperService.getMaybeRemapSourceMinLen(), mcRemapperService.getMaybeRemapSourceMaxLen());
    }

}
