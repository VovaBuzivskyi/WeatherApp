package com.vovabuzivskyi.weatherapp.service;

import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    WeatherConfig weatherConfig;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    WeatherService weatherService;

    private final String city = "London";
    private final String weatherURL = "https://api.openweathermap.org/data/2.5/weather?q=London&appid=testKey";

    @BeforeEach
    void setUp(){
        when(weatherConfig.getWeatherUrl()).thenReturn("https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}");
        when(weatherConfig.getApiKey()).thenReturn("testKey");
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void testGetWeather(){
        WeatherResponse mockResponse = new WeatherResponse();
        when(restTemplate.getForObject(weatherURL, WeatherResponse.class)).thenReturn(mockResponse);

        WeatherResponse response = weatherService.getWeather(city);

        assertEquals(mockResponse, response);
        verify(weatherConfig).getWeatherUrl();
        verify(weatherConfig).getApiKey();
        verify(restTemplate).getForObject(weatherURL,WeatherResponse.class);
    }


}