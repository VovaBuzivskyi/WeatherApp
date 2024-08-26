package com.vovabuzivskyi.weatherapp.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherConfigTest {

    WeatherConfig weatherConfig;

    @BeforeEach
    void setUp() {
        weatherConfig = new WeatherConfig();
        weatherConfig.setApiKey("YOUR_API_KEY");
        weatherConfig.setWeatherUrl("YOUR_WEATHER_URL");
        weatherConfig.setForecastUrl("YOUR_FORECAST_URL");
    }

    @Test
    void getApiKey() {
        assertEquals("YOUR_API_KEY", weatherConfig.getApiKey());
    }

    @Test
    void setApiKey() {
        weatherConfig.setApiKey("OTHER_API_KEY");

        assertEquals("OTHER_API_KEY", weatherConfig.getApiKey());
    }

    @Test
    void getWeatherUrl() {
        assertEquals("YOUR_WEATHER_URL", weatherConfig.getWeatherUrl());
    }

    @Test
    void setWeatherUrl() {
        weatherConfig.setWeatherUrl("OTHER_WEATHER_URL");

        assertEquals("OTHER_WEATHER_URL", weatherConfig.getWeatherUrl());
    }

    @Test
    void getForecastUrl() {
        assertEquals("YOUR_API_KEY", weatherConfig.getApiKey());
    }

    @Test
    void setForecastUrl() {
        weatherConfig.setForecastUrl("OTHER_FORECAST_URL");

        assertEquals("OTHER_FORECAST_URL", weatherConfig.getForecastUrl());
    }
}