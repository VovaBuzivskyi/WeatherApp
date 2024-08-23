package com.vovabuzivskyi.weatherapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlerCityNotFound {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerCityNotFound.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException e, Model model) {
        model.addAttribute("errorMessage", "City with such name doesn't exist, check the spelling");

        log.warn("City wasn't found");
        return "error_page";
    }
}