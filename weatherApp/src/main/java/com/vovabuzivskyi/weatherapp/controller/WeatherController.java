package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;


@Controller()
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get_weather")
    public String getWeather() {
        log.debug("Method getWeather(Controller) started");
        return "get_weather";
    }

    @GetMapping("/show_weather")
    public String showWeather(@RequestParam(name = "city") String city, Model model) {
        log.debug("Method showWeather(Controller) started for city: {} ",city);

        WeatherResponse weatherData = weatherService.getWeather(city);
        if (weatherData != null) {
            model.addAttribute("weatherData", weatherData);

            log.debug("Method showWeather(Controller) send data to View show_weather city: {} ",city);

            return "show_weather";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }

}


