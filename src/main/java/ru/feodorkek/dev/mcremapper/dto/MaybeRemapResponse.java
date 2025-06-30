package ru.feodorkek.dev.mcremapper.dto;

import lombok.Data;
import ru.feodorkek.dev.mcremapper.core.MaybeRemapResult;

@Data
public class MaybeRemapResponse {

    private final MaybeRemapResult result;

}
