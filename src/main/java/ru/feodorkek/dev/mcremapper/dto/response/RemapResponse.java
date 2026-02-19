package ru.feodorkek.dev.mcremapper.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import ru.feodorkek.dev.mcremapper.dto.RemapResult;

@Getter
@ToString
@RequiredArgsConstructor
public class RemapResponse {
    
    @Schema( description = "The result of the remapping process" )
    private final RemapResult result;

}