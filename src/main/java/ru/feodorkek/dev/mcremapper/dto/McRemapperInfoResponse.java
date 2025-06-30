package ru.feodorkek.dev.mcremapper.dto;

import lombok.Data;

import java.util.List;

@Data
public class McRemapperInfoResponse {

    private final List<String> mcVersions;
    private final int sourceMinLen;
    private final int sourceMaxLen;

}
