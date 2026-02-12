package ru.feodorkek.dev.mcremapper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.mcremapper.controller.mvc.MvcPackageMarker;

@Slf4j
@ControllerAdvice( basePackageClasses = MvcPackageMarker.class )
public class MvcControllerAdvice {
    
    @ExceptionHandler
    public String handleException( final Exception exception,
                                   final Model model ) {
        log.error( "Mvc controller exception occurred", exception );
        
        model.addAttribute( "error", exception.getMessage() );
        return "error";
    }
    
}
