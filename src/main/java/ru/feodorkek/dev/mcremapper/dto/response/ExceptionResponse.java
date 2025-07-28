package ru.feodorkek.dev.mcremapper.dto.response;

import lombok.Value;

@Value
public class ExceptionResponse {

    String errorType;
    String errorMessage;

}
