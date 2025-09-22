package ru.feodorkek.dev.mcremapper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.mcremapper.controller.rest.ControllerPackage;
import ru.feodorkek.dev.mcremapper.dto.response.ExceptionResponse;

@Slf4j
@ControllerAdvice(basePackageClasses = ControllerPackage.class)
public class RestControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleGenericException(final Exception exception) {
        final var exceptionType = exception.getClass().getSimpleName();
        log.error("RestController exception occurred: [{}]: {}", exceptionType, exception.getMessage());
        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(exceptionType, exception.getMessage()));
    }

}
