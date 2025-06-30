package ru.feodorkek.dev.mcremapper.usecase;

import ru.feodorkek.dev.mcremapper.dto.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.dto.McRemapperInfoResponse;

public interface McRemapperUseCases {

    MaybeRemapResponse maybeRemap(MaybeRemapRequest request);

    McRemapperInfoResponse getInfo();

}
