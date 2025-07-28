package ru.feodorkek.dev.mcremapper.dto.response;

import lombok.Value;

import java.util.List;

@Value
public class McRemapperInfoResponse {

    List<String> mcVersions;
    int sourceMinLen;
    int sourceMaxLen;

}
