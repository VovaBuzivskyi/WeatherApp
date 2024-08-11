package com.vovabuzivskyi.weatherapp.service;


import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    private final WeatherConfig weatherConfig;
    private final RestTemplateBuilder restTemplateBuilder;

    public WeatherService(WeatherConfig weatherConfig, RestTemplateBuilder restTemplateBuilder) {
        this.weatherConfig = weatherConfig;
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public WeatherResponse getWeather(String city) {
        String weatherUrl = weatherConfig.getWeatherUrl().replace("{city}", city).replace("{apiKey}", weatherConfig.getApiKey());
        return restTemplateBuilder.build().getForObject(weatherUrl, WeatherResponse.class);
    }

    public ForecastResponse getForecast(String city) {
        String forecastUrl = weatherConfig.getForecastUrl().replace("{city}", city).replace("{apiKey}", weatherConfig.getApiKey());
        return restTemplateBuilder.build().getForObject(forecastUrl, ForecastResponse.class);

    }

}
