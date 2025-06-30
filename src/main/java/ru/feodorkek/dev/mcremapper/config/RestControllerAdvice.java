package ru.feodorkek.dev.mcremapper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.mcremapper.controller.rest.RestControllerPackage;
import ru.feodorkek.dev.mcremapper.dto.ExceptionResponse;

@Slf4j
@ControllerAdvice(basePackageClasses = RestControllerPackage.class)
public class RestControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(final Exception exception) {
        log.error("RestController error occurred", exception);
        return ResponseEntity.internalServerError().body(new ExceptionResponse(exception.getMessage()));
    }

}
