package ru.feodorkek.dev.mcremapper.dto.request;

import lombok.Value;

@Value
public class MaybeRemapRequest {

    String mcRemapperProviderName;
    String mappedSource;

}
