package ru.feodorkek.dev.mcremapper.dto;

import lombok.Data;

@Data
public class ExceptionResponse {

    private final String errorType;
    private final String errorMessage;

}
