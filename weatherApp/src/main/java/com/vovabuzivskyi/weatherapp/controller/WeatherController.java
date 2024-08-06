package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller()
public class WeatherController {

    WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get_weather")
    public String getWeather(@RequestParam(name = "city", required = false) String city) {
        return "homePage";
    }

    @GetMapping("/weather")
    public String weather(@RequestParam(name = "city") String city, Model model) {
        WeatherResponse weatherData = weatherService.getWeather(city);
        if (weatherData != null) {
            model.addAttribute("city", weatherData.getName());
            model.addAttribute("country", weatherData.getSys().getCountry());
            model.addAttribute("weatherDescription", weatherData.getWeather().get(0).getDescription());
            model.addAttribute("temperature", weatherData.getMain().getTemp());
            model.addAttribute("humidity", weatherData.getMain().getHumidity());
            model.addAttribute("windSpeed", weatherData.getWind().getSpeed());
        } else {
            model.addAttribute("error", "Not found such city");
        }
        return "show_weather";
    }

}


