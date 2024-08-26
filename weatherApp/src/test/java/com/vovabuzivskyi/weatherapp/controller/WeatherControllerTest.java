package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WeatherService weatherService;

    @InjectMocks
    WeatherController weatherController;

    @Test
    void testGetWeatherShouldPerformGetRequest() throws Exception {
        mockMvc.perform(get("/get_weather"))
                .andExpect(status().isOk())
                .andExpect(view().name("get_weather"))
                .andReturn();
    }

    @Test
    void testShowWeatherWithValidCity() throws Exception {
        String city = "Kiev";
        WeatherResponse weatherResponse = new WeatherResponse();

        WeatherResponse.Sys sys = new WeatherResponse.Sys();
        sys.setCountry("UA");
        weatherResponse.setSys(sys);

        WeatherResponse.Weather weather = new WeatherResponse.Weather();
        weather.setDescription("Description");

        List<WeatherResponse.Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        weatherResponse.setWeather(weatherList);

        WeatherResponse.Main main = new WeatherResponse.Main();
        main.setTemp(12);
        main.setHumidity(52);
        weatherResponse.setMain(main);

        WeatherResponse.Wind wind = new WeatherResponse.Wind();
        wind.setSpeed(12.2);
        weatherResponse.setWind(wind);

        when(weatherService.getWeather(city)).thenReturn(weatherResponse);

        mockMvc.perform(get("/show_weather").param("city", city))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("weatherData"))
                .andExpect(view().name("show_weather"));
    }

    @Test
    void testShowWeatherWithInvalidCity() throws Exception {
        String city =  "InvalidCity";
        when(weatherService.getWeather(anyString())).thenReturn(null);

        mockMvc.perform(get("/show_weather").param("city",city))
                .andExpect(status().isOk())
                .andExpect(view().name("error_page"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "City with such name doesn't exist, check the spelling"));
    }

}