package ru.feodorkek.dev.mcremapper.business;

import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoIn;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapDtoOut;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoDtoOut;

public interface McRemapperUseCases {

    MaybeRemapDtoOut maybeRemap(MaybeRemapDtoIn maybeRemapDtoIn);

    McRemapperInfoDtoOut getInfo();

}
