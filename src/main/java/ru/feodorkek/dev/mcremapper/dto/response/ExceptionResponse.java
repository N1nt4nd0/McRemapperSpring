package ru.feodorkek.dev.mcremapper.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ExceptionResponse {
    
    @Schema( description = "The type of the exception",
             example = "java.lang.NullPointerException" )
    private final String errorType;
    
    @Schema( description = "The message of the exception",
             example = "Cannot invoke String.length() because str is null" )
    private final String errorMessage;
    
}