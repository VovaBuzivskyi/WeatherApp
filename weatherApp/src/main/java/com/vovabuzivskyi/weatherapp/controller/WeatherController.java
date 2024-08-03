package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

WeatherService weatherService;
WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
}

    @GetMapping("/weather")
    public String weather(@RequestParam (name = "city" ,required = false) String city, Model model) {
        String weatherData = weatherService.getWeather(city);
        model.addAttribute("weatherData", weatherData);
        model.addAttribute("city", city);
        return "showWeather" ;
    }


}
