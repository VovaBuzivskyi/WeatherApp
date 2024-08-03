package com.vovabuzivskyi.weatherapp.service;


import com.vovabuzivskyi.weatherapp.configuration.WeatherConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    private final WeatherConfig weatherConfig;
    private final RestTemplateBuilder restTemplateBuilder;

    public WeatherService(RestTemplateBuilder restTemplateBuilder, WeatherConfig weatherConfig) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.weatherConfig = weatherConfig;

    }

    public String getWeather(String city) {
        String url = weatherConfig.getUrl().replace("{city}", city).replace("{apiKey}", weatherConfig.getApiKey());
        return restTemplateBuilder.build().getForObject(url, String.class);
    }
}
