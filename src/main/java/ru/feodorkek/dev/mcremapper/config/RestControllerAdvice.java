package ru.feodorkek.dev.mcremapper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.mcremapper.controller.rest.RestPackageMarker;
import ru.feodorkek.dev.mcremapper.dto.response.ExceptionResponse;

@Slf4j
@ControllerAdvice( basePackageClasses = RestPackageMarker.class )
public class RestControllerAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleGenericException( final Exception exception ) {
        log.error( "Rest controller exception occurred", exception );
        
        return ResponseEntity.internalServerError()
                             .body( new ExceptionResponse( exception.getClass().getSimpleName(), exception.getMessage() ) );
    }
    
}