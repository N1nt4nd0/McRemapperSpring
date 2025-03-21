package ru.feodorkek.dev.mcremapper.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.mcremapper.business.McRemapperUseCases;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoIn;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoOut;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoDtoOut;
import ru.feodorkek.dev.mcremapper.service.McRemapperService;

@Service
@RequiredArgsConstructor
public class McRemapperUseCasesImpl implements McRemapperUseCases {

    private final McRemapperService mcRemapperService;

    @Override
    public MaybeRemapDtoOut maybeRemap(final MaybeRemapDtoIn maybeRemapDtoIn) {
        mcRemapperService.setCurrentProvider(maybeRemapDtoIn.getMcRemapperProviderName());
        mcRemapperService.validateMappedSource(maybeRemapDtoIn.getMappedSource());
        return new MaybeRemapDtoOut(mcRemapperService.maybeRemap(maybeRemapDtoIn.getMappedSource()));
    }

    @Override
    public McRemapperInfoDtoOut getInfo() {
        return new McRemapperInfoDtoOut(mcRemapperService.getRegisteredProviderNames(),
                mcRemapperService.getMaybeRemapSourceMinLen(), mcRemapperService.getMaybeRemapSourceMaxLen());
    }

}
