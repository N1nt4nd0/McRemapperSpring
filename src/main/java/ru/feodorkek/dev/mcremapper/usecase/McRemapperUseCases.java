package ru.feodorkek.dev.mcremapper.usecase;

import ru.feodorkek.dev.mcremapper.dto.request.MaybeRemapRequest;
import ru.feodorkek.dev.mcremapper.dto.response.MaybeRemapResponse;
import ru.feodorkek.dev.mcremapper.dto.response.McRemapperInfoResponse;

public interface McRemapperUseCases {

    MaybeRemapResponse maybeRemap(MaybeRemapRequest request);

    McRemapperInfoResponse getInfo();

}
