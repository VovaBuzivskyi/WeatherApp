package com.vovabuzivskyi.weatherapp.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = ExceptionHandlerCityNotFound.class)
class ExceptionHandlerCityNotFoundTest {

    @Autowired
   private MockMvc mockMvc;

    @Test
    void testHandleHttpClientErrorException() {

        Model model = mock(Model.class);

        ExceptionHandlerCityNotFound exceptionHandlerCityNotFound = new ExceptionHandlerCityNotFound();
        String viewName = exceptionHandlerCityNotFound.handleHttpClientErrorException(model);

        assertEquals("error_page",viewName);
        verify(model).addAttribute("errorMessage","City with such name doesn't exist, check the spelling");
    }
}