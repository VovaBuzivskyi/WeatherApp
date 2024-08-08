package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;


@Controller()
public class WeatherController {

    WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get_weather")
    public String getWeather(@RequestParam(name = "city", required = false) String city) {
        return "get_weather";
    }

    @GetMapping("/show_weather")
    public String weather(@RequestParam(name = "city") String city, Model model) {
        WeatherResponse weatherData = weatherService.getWeather(city);
        if (weatherData != null) {
            model.addAttribute("city", weatherData.getName());
            model.addAttribute("country", weatherData.getSys().getCountry());
            model.addAttribute("weatherDescription", weatherData.getWeather().get(0).getDescription());
            model.addAttribute("temperature", weatherData.getMain().getTemp());
            model.addAttribute("humidity", weatherData.getMain().getHumidity());
            model.addAttribute("windSpeed", weatherData.getWind().getSpeed());
            return "show_weather";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }

    @GetMapping("/get_forecast")
    public String getForecast(@RequestParam(name = "city", required = false) String city) {
        return "get_forecast";
    }

    @GetMapping("/show_forecast")
    public String forecast(@RequestParam String city, Model model) {
        ForecastResponse forecastData = weatherService.getForecast(city);
        if (forecastData != null) {
            model.addAttribute("cityData", forecastData.getCity());
            model.addAttribute("datalist", forecastData.getList());
            return "show_forecast";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException e, Model model) {
        model.addAttribute("errorMessage", "City with such name doesn't exist");
        return "error_page";
    }
}


