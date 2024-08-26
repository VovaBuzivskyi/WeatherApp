package com.vovabuzivskyi.weatherapp.service;


import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherConfig weatherConfig;
    private final RestTemplateBuilder restTemplateBuilder;

    public WeatherService(WeatherConfig weatherConfig, RestTemplateBuilder restTemplateBuilder) {
        this.weatherConfig = weatherConfig;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public WeatherResponse getWeather(String city) {
        log.debug("Method getWeather(WeatherService): city = {}", city + " started work");
        String weatherUrl = weatherConfig.getWeatherUrl().replace("{city}", city).replace("{apiKey}", weatherConfig.getApiKey());
        log.debug("Weather received from weather API for city: {}", city);
        return restTemplateBuilder.build().getForObject(weatherUrl, WeatherResponse.class);
    }
}
