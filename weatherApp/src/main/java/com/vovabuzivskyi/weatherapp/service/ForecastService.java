package com.vovabuzivskyi.weatherapp.service;

import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

@Service
public class ForecastService {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherConfig weatherConfig;
    private final RestTemplateBuilder restTemplateBuilder;

    public ForecastService(WeatherConfig weatherConfig, RestTemplateBuilder restTemplateBuilder) {
        this.weatherConfig = weatherConfig;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ForecastResponse getForecast(String city) {
        String forecastUrl = weatherConfig.getForecastUrl().replace("{city}", city).replace("{apiKey}", weatherConfig.getApiKey());
        log.debug("Forecast received from weather API for city: {}", city);
        return restTemplateBuilder.build().getForObject(forecastUrl, ForecastResponse.class);

    }

}
