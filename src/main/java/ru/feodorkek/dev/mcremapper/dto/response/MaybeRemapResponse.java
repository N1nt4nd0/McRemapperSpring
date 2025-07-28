package ru.feodorkek.dev.mcremapper.dto.response;

import lombok.Value;
import ru.feodorkek.dev.mcremapper.core.objects.MaybeRemapResult;

@Value
public class MaybeRemapResponse {

    MaybeRemapResult result;

}
