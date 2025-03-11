package ru.feodorkek.dev.mcremapper.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.mcremapper.controller.mvc.MvcControllerPackage;

@Slf4j
@ControllerAdvice(basePackageClasses = MvcControllerPackage.class)
public class MvcControllerExceptionHandler {

    @ExceptionHandler
    public String handleException(final Exception exception, final Model model) {
        model.addAttribute("error", exception.getMessage());
        log.error("MvcController error occurred", exception);
        return "error";
    }

}
