package ru.feodorkek.dev.mcremapper.business;

import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoIn;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoDtoOut;
import ru.feodorkek.dev.mcremapper.mcremapper.MaybeRemapResult;

public interface McRemapperUseCases {

    MaybeRemapResult maybeRemap(MaybeRemapDtoIn maybeRemapDtoIn);

    McRemapperInfoDtoOut getInfo();

}
