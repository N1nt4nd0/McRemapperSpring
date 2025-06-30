package ru.feodorkek.dev.mcremapper.dto;

import lombok.Data;

@Data
public class MaybeRemapRequest {

    private final String mcRemapperProviderName;
    private final String mappedSource;

}
