package com.vovabuzivskyi.weatherapp.service;

import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {

    @Mock
    WeatherConfig weatherConfig;

    @Mock
    RestTemplateBuilder restTemplateBuilder;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    ForecastService forecastService;

    private final String city = "London";
    private final String forecastUrl = "https://api.openweathermap.org/data/2.5/forecast?q=London&appid=testKey&units=metric";

    @BeforeEach
    void setUp() {
        when(weatherConfig.getForecastUrl()).thenReturn("https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={apiKey}&units=metric");
        when(weatherConfig.getApiKey()).thenReturn("testKey");
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
    }

    @Test
    void testForecastService(){
        ForecastResponse mockForecastResponse = new ForecastResponse();
        when(restTemplate.getForObject(forecastUrl,ForecastResponse.class)).thenReturn(mockForecastResponse);

        ForecastResponse forecastResponse = forecastService.getForecast(city);

        assertEquals(mockForecastResponse, forecastResponse);
        verify(weatherConfig).getForecastUrl();
        verify(weatherConfig).getApiKey();
        verify(restTemplate).getForObject(forecastUrl,ForecastResponse.class);

    }
}