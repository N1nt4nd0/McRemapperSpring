package ru.feodorkek.dev.mcremapper.dto;

import lombok.Data;
import ru.feodorkek.dev.mcremapper.core.objects.MaybeRemapResult;

@Data
public class MaybeRemapResponse {

    private final MaybeRemapResult result;

}
